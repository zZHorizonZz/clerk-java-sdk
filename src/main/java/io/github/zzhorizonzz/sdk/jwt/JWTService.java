package io.github.zzhorizonzz.sdk.jwt;

import io.github.zzhorizonzz.sdk.BaseService;
import io.github.zzhorizonzz.sdk.ClerkClient;
import io.github.zzhorizonzz.sdk.session.SessionClaims;
import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jwk.PublicJsonWebKey;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.jwt.consumer.JwtContext;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Service class for handling JWT (JSON Web Tokens) related operations.
 * This class provides functionalities for decoding and verifying JWTs.
 */
public class JWTService extends BaseService {

    private static final Set<String> STANDARD_CLAIMS_KEYS = Set.of("iss", "sub", "aud", "exp", "nbf", "iat", "jti");

    public JWTService(ClerkClient clerkClient) {
        super(clerkClient);
    }

    /**
     * Decodes a JWT token and extracts its claims.
     *
     * @param token The JWT token to decode.
     * @return TokenClaims object containing the decoded claims.
     * @throws Exception If the token processing fails.
     */
    public TokenClaims decodeToken(String token) throws Exception {
        JwtConsumer jwtConsumer = new JwtConsumerBuilder()
                .setSkipAllValidators()
                .setDisableRequireSignature()
                .setSkipSignatureVerification()
                .build();

        JwtContext jwtContext = jwtConsumer.process(token);
        JwtClaims jwtClaims = jwtContext.getJwtClaims();

        Map<String, Object> allClaims = jwtClaims.getClaimsMap();
        Map<String, Object> extraClaims = new HashMap<>(allClaims);
        STANDARD_CLAIMS_KEYS.forEach(extraClaims::remove);

        return new TokenClaims(jwtClaims, extraClaims);
    }

    /**
     * Verifies a JWT token using the provided options and returns its session claims.
     * This method includes validation for expiration, signature, and issuer.
     *
     * @param token The JWT token to verify.
     * @param opts  The verification options.
     * @return SessionClaims object after successful verification.
     * @throws Exception If token verification fails.
     */
    public SessionClaims verifyToken(String token, VerifyTokenOptions... opts) throws Exception {
        VerifyTokenOptions options = new VerifyTokenOptions();

        for (VerifyTokenOptions opt : opts) {
            options.apply(opt);
        }

        // First pass to retrieve the 'kid' from the JWT header
        JwtConsumer firstPassJwtConsumer = new JwtConsumerBuilder()
                .setSkipAllValidators()
                .setDisableRequireSignature()
                .setSkipSignatureVerification()
                .build();

        JwtContext jwtContext = firstPassJwtConsumer.process(token);
        String kid = jwtContext.getJoseObjects().get(0).getKeyIdHeaderValue();

        // Retrieve the public key using the 'kid'
        JsonWebKey jsonWebKey = getClerkClient().getJwksService().getJWK(kid);
        PublicKey publicKey = convertToPublicKey(jsonWebKey);

        // Second pass for actual verification
        JwtConsumer jwtConsumer = new JwtConsumerBuilder()
                .setVerificationKey(publicKey)
                .setRequireExpirationTime()
                .setAllowedClockSkewInSeconds((int) options.getLeeway())
                // .setExpectedIssuer(options.getIssuer()) // Uncomment and set as per options if needed
                // .setExpectedAudience(options.getAudience()) // Uncomment and set as per options if needed
                .build();

        JwtClaims jwtClaims = jwtConsumer.processToClaims(token);
        SessionClaims sessionClaims = new SessionClaims(jwtClaims);

        // Additional validations
        validateTokenExpiry(jwtClaims, options);
        validateTokenIssuer(jwtClaims, options);

        return sessionClaims;
    }

    /**
     * Verifies a JWT token, parses its claims and returns a SessionClaims object.
     *
     * @param jwtToken The JWT token to verify.
     * @param key      The JsonWebKey used for verification.
     * @param options  The verification options.
     * @return SessionClaims object containing the claims.
     * @throws Exception If JWT processing fails.
     */
    public static SessionClaims verifyTokenParseClaims(String jwtToken, JsonWebKey key, VerifyTokenOptions options)
            throws Exception {
        JwtConsumer jwtConsumer = new JwtConsumerBuilder()
                .setVerificationKey(key.getKey())
                .setRequireExpirationTime()
                .setAllowedClockSkewInSeconds((int) options.getLeeway())
                .build();

        JwtClaims jwtClaims;
        try {
            jwtClaims = jwtConsumer.processToClaims(jwtToken);
        } catch (Exception e) {
            throw new Exception("Error processing JWT claims", e);
        }

        return new SessionClaims(jwtClaims);
    }

    /**
     * Converts a JsonWebKey to a PublicKey.
     *
     * @param jwk The JsonWebKey to convert.
     * @return The corresponding PublicKey.
     * @throws IllegalArgumentException If the provided JsonWebKey is not a public key.
     */
    private PublicKey convertToPublicKey(JsonWebKey jwk) {
        if (jwk instanceof PublicJsonWebKey) {
            return ((PublicJsonWebKey) jwk).getPublicKey();
        }
        throw new IllegalArgumentException("Provided JsonWebKey is not a public key");
    }

    /**
     * Validates the expiration time of the JWT.
     *
     * @param jwtClaims The JWT claims.
     * @param options   The verification options.
     * @throws Exception If the token has expired.
     */
    private void validateTokenExpiry(JwtClaims jwtClaims, VerifyTokenOptions options) throws Exception {
        long expirationTimeMillis = jwtClaims.getExpirationTime().getValueInMillis();
        long currentTimeMillis = System.currentTimeMillis();
        long leeway = options.getLeeway();

        if (expirationTimeMillis + leeway < currentTimeMillis) {
            throw new Exception("Token has expired");
        }
    }

    /**
     * Validates the issuer of the JWT.
     *
     * @param jwtClaims The JWT claims.
     * @param options   The verification options.
     * @throws Exception If the issuer is invalid or not authorized.
     */
    private void validateTokenIssuer(JwtClaims jwtClaims, VerifyTokenOptions options) throws Exception {
        String issuer = jwtClaims.getIssuer();

        TokenIssuer tokenIssuer = new TokenIssuer(jwtClaims.getIssuer()).withSatelliteDomain(options.isSatellite())
                .withProxyURL(options.getProxyURL());
        if (!tokenIssuer.isValid()) {
            throw new Exception("Invalid issuer " + jwtClaims.getIssuer());
        }

        // Validate against a set of authorized parties if provided
        if (options.getAuthorizedParties() != null && !options.getAuthorizedParties().isEmpty()) {
            if (!options.getAuthorizedParties().containsKey(issuer)) {
                throw new Exception("Issuer " + issuer + " is not an authorized party");
            }
        }
    }
}

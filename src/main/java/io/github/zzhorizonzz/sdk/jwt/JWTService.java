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

public class JWTService extends BaseService {

    private static final Set<String> STANDARD_CLAIMS_KEYS = Set.of("iss", "sub", "aud", "exp", "nbf", "iat", "jti");

    public JWTService(ClerkClient clerkClient) {
        super(clerkClient);
    }

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

    public SessionClaims verifyToken(String token, VerifyTokenOptions options) throws Exception {
        JwtConsumer firstPassJwtConsumer = new JwtConsumerBuilder()
                .setSkipAllValidators()
                .setDisableRequireSignature()
                .setSkipSignatureVerification()
                .build();

        JwtContext jwtContext = firstPassJwtConsumer.process(token);
        String kid = jwtContext.getJoseObjects().get(0).getKeyIdHeaderValue();

        JsonWebKey jsonWebKey = getClerkClient().getJWK(kid);
        PublicKey publicKey = convertToPublicKey(jsonWebKey);

        JwtConsumer jwtConsumer = new JwtConsumerBuilder()
                .setVerificationKey(publicKey)
                .setRequireExpirationTime()
                .setAllowedClockSkewInSeconds((int) options.getLeeway())
                //.setExpectedIssuer(options.getIssuer()) // Set as per options
                //.setExpectedAudience(options.getAudience()) // Set as per options
                .build();

        JwtClaims jwtClaims = jwtConsumer.processToClaims(token);
        SessionClaims sessionClaims = new SessionClaims(jwtClaims);

        if (jwtClaims.getExpirationTime().getValueInMillis() + options.getLeeway() < System.currentTimeMillis()) {
            throw new Exception("Token has expired");
        }

        TokenIssuer issuer = new TokenIssuer(jwtClaims.getIssuer()).withSatelliteDomain(options.isSatellite())
                .withProxyURL(options.getProxyURL());
        if (!issuer.isValid()) {
            throw new Exception("Invalid issuer " + jwtClaims.getIssuer());
        }

        if (options.getAuthorizedParties() != null && !options.getAuthorizedParties().isEmpty()) {
            if (!options.getAuthorizedParties().containsKey(jwtClaims.getIssuer())) {
                throw new Exception("Invalid authorized party " + jwtClaims.getIssuer());
            }
        }

        return sessionClaims;
    }

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

    private PublicKey convertToPublicKey(JsonWebKey jwk) throws Exception {
        if (jwk instanceof PublicJsonWebKey) {
            return ((PublicJsonWebKey) jwk).getPublicKey();
        }
        throw new IllegalArgumentException("Provided JsonWebKey is not a public key");
    }
}

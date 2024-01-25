package io.github.zzhorizonzz.sdk.jwk;

import io.github.zzhorizonzz.sdk.BaseService;
import io.github.zzhorizonzz.sdk.ClerkClient;
import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jwk.JsonWebKeySet;

import java.io.InputStream;

/**
 * Service class for managing JSON Web Keys (JWKs).
 * This class provides functionalities to retrieve and manage JWKs,
 * including caching for efficiency.
 */
public class JWKService extends BaseService {

    private final JWKCache jwksCache;

    /**
     * Constructs a JWKService with the given ClerkClient.
     *
     * @param clerkClient The ClerkClient instance used for communication with backend services.
     */
    public JWKService(ClerkClient clerkClient) {
        super(clerkClient);
        this.jwksCache = new JWKCache();
    }

    /**
     * Retrieves all JSON Web Keys (JWKs) from the server.
     *
     * @return JsonWebKeySet containing all the JWKs.
     * @throws Exception If there's an issue retrieving the JWKs.
     */
    public JsonWebKeySet listAll() throws Exception {
        InputStream keys = getHttpClient().jwks().get();
        if (keys == null) {
            return null;
        }

        String jwks = new String(keys.readAllBytes());
        return new JsonWebKeySet(jwks);
    }

    /**
     * Retrieves a specific JSON Web Key (JWK) based on the Key ID (kid).
     * If the key is not found in the cache, it refreshes the cache from the server.
     *
     * @param kid The Key ID of the JWK to retrieve.
     * @return The JsonWebKey corresponding to the provided Key ID.
     * @throws Exception If there's an issue retrieving the JWK.
     */
    public JsonWebKey getJWK(String kid) throws Exception {
        if (jwksCache.isInvalid()) {
            JsonWebKeySet jwks = listAll();
            jwksCache.set(jwks);
        }

        return jwksCache.get(kid);
    }
}

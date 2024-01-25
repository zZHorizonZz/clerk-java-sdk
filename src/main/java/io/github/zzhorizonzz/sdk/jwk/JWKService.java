package io.github.zzhorizonzz.sdk.jwk;

import io.github.zzhorizonzz.sdk.BaseService;
import io.github.zzhorizonzz.sdk.ClerkClient;
import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jwk.JsonWebKeySet;

import java.io.InputStream;

public class JWKService extends BaseService {

    private final JWKCache jwksCache;

    public JWKService(ClerkClient clerkClient) {
        super(clerkClient);
        this.jwksCache = new JWKCache();
    }

    public JsonWebKeySet listAll() throws Exception {
        InputStream keys = getHttpClient().jwks().get();
        if (keys == null) {
            return null;
        }

        String jwks = new String(keys.readAllBytes());
        return new JsonWebKeySet(jwks);
    }

    public JsonWebKey getJWK(String kid) throws Exception {
        if (jwksCache.isInvalid()) {
            JsonWebKeySet jwks = listAll();
            jwksCache.set(jwks);
        }

        return jwksCache.get(kid);
    }
}

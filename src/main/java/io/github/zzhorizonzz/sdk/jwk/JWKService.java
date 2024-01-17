package io.github.zzhorizonzz.sdk.jwk;

import io.github.zzhorizonzz.sdk.BaseService;
import io.github.zzhorizonzz.sdk.ClerkClient;
import org.jose4j.jwk.JsonWebKeySet;

import java.io.InputStream;

public class JWKService extends BaseService {

    public JWKService(ClerkClient clerkClient) {
        super(clerkClient);
    }

    public JsonWebKeySet listAll() throws Exception {
        InputStream keys = getHttpClient().jwks().get();
        String jwks = new String(keys.readAllBytes());
        return new JsonWebKeySet(jwks);
    }
}

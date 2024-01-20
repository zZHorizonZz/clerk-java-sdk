package io.github.zzhorizonzz.sdk;

import com.microsoft.kiota.RequestAdapter;
import io.github.zzhorizonzz.sdk.jwk.JWKCache;
import io.github.zzhorizonzz.sdk.jwk.JWKService;
import io.github.zzhorizonzz.sdk.jwt.JWTService;
import io.github.zzhorizonzz.sdk.jwt.VerifyTokenOptions;
import io.github.zzhorizonzz.sdk.session.SessionClaims;
import io.github.zzhorizonzz.sdk.user.UserService;
import lombok.Getter;
import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jwk.JsonWebKeySet;

@Getter
public class ClerkClient {
    private final JWKCache jwksCache;
    private final JWKService jwksService;
    private final UserService userService;
    private final RequestAdapter requestAdapter;

    public ClerkClient(RequestAdapter requestAdapter) {
        this.requestAdapter = requestAdapter;

        this.jwksCache = new JWKCache();
        this.jwksService = new JWKService(this);
        this.userService = new UserService(this);
    }

    public SessionClaims verifyToken(String token) throws Exception {
        JWTService jwtService = new JWTService(this);
        return jwtService.verifyToken(token, new VerifyTokenOptions());
    }

    public JsonWebKey getJWK(String kid) throws Exception {
        if (jwksCache.isInvalid()) {
            JsonWebKeySet jwks = jwksService.listAll();
            jwksCache.set(jwks);
        }

        return jwksCache.get(kid);
    }
}

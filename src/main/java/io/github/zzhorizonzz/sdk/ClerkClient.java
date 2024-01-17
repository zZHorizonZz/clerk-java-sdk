package io.github.zzhorizonzz.sdk;

import com.microsoft.kiota.authentication.AccessTokenProvider;
import com.microsoft.kiota.authentication.AllowedHostsValidator;
import io.github.zzhorizonzz.sdk.jwk.JWKCache;
import io.github.zzhorizonzz.sdk.jwk.JWKService;
import io.github.zzhorizonzz.sdk.jwt.JWTService;
import io.github.zzhorizonzz.sdk.jwt.VerifyTokenOptions;
import io.github.zzhorizonzz.sdk.session.SessionClaims;
import io.github.zzhorizonzz.sdk.user.UserService;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jwk.JsonWebKeySet;

import java.net.URI;
import java.util.Map;

@Getter
public class ClerkClient implements AccessTokenProvider {
    private final String token;
    private final JWKCache jwksCache;
    private final JWKService jwksService;
    private final UserService userService;

    public ClerkClient(String token) {
        this.token = token;
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

    @NotNull
    @Override
    public String getAuthorizationToken(@NotNull URI uri, @Nullable Map<String, Object> additionalAuthenticationContext) {
        return token;
    }

    @NotNull
    @Override
    public AllowedHostsValidator getAllowedHostsValidator() {
        return new AllowedHostsValidator();
    }
}

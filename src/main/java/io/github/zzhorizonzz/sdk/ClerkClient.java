package io.github.zzhorizonzz.sdk;

import com.microsoft.kiota.RequestAdapter;
import io.github.zzhorizonzz.sdk.jwk.JWKService;
import io.github.zzhorizonzz.sdk.jwt.JWTService;
import io.github.zzhorizonzz.sdk.user.UserService;
import lombok.Getter;

@Getter
public class ClerkClient {
    private final JWKService jwksService;
    private final JWTService jwtService;
    private final UserService userService;
    private final RequestAdapter requestAdapter;

    public ClerkClient(RequestAdapter requestAdapter) {
        this.requestAdapter = requestAdapter;

        this.jwksService = new JWKService(this);
        this.jwtService = new JWTService(this);
        this.userService = new UserService(this);
    }
}

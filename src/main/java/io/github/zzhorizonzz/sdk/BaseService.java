package io.github.zzhorizonzz.sdk;

import com.microsoft.kiota.RequestAdapter;
import com.microsoft.kiota.authentication.AuthenticationProvider;
import com.microsoft.kiota.authentication.BaseBearerTokenAuthenticationProvider;
import com.microsoft.kiota.http.OkHttpRequestAdapter;
import io.github.zzhorizonzz.ClerkHttpClient;
import lombok.Getter;

@Getter
public abstract class BaseService {

    private final ClerkClient clerkClient;
    private final AuthenticationProvider authProvider;
    private final RequestAdapter requestAdapter;
    private final ClerkHttpClient httpClient;

    public BaseService(ClerkClient clerkClient) {
        this.clerkClient = clerkClient;
        this.authProvider = new BaseBearerTokenAuthenticationProvider(clerkClient);
        this.requestAdapter = new OkHttpRequestAdapter(authProvider);
        this.httpClient = new ClerkHttpClient(requestAdapter);
    }
}

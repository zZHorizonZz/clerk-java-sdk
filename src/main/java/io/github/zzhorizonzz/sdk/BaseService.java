package io.github.zzhorizonzz.sdk;

import io.github.zzhorizonzz.client.ClerkHttpClient;
import lombok.Getter;

@Getter
public abstract class BaseService {

    private final ClerkClient clerkClient;
    private final ClerkHttpClient httpClient;

    public BaseService(ClerkClient clerkClient) {
        this.clerkClient = clerkClient;
        this.httpClient = new ClerkHttpClient(clerkClient.getRequestAdapter());
    }
}

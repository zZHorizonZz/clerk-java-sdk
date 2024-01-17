package io.github.zzhorizonzz.sdk.jwt;

/*
 * @source: https://github.com/clerk/clerk-sdk-go/blob/main/clerk/tokens_issuer.go
 */
public class TokenIssuer {
    private final String iss;
    private boolean isSatellite;
    private String proxyURL;

    public TokenIssuer(String iss) {
        this.iss = iss;
    }

    public TokenIssuer withSatelliteDomain(boolean isSatellite) {
        this.isSatellite = isSatellite;
        return this;
    }

    public TokenIssuer withProxyURL(String proxyURL) {
        this.proxyURL = proxyURL;
        return this;
    }

    public boolean isValid() {
        if (isSatellite) {
            return true;
        }

        if (proxyURL != null && !proxyURL.isEmpty()) {
            return iss.equals(proxyURL);
        }

        return iss.startsWith("https://clerk.") || iss.contains(".clerk.accounts");
    }
}

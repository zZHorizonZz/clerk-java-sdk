package io.github.zzhorizonzz.sdk.jwt;

/**
 * Represents and validates the issuer of a JWT (JSON Web Token).
 * This class facilitates the validation of the 'iss' (issuer) claim based on specific criteria.
 * <p>
 * Source: <a href="https://github.com/clerk/clerk-sdk-go/blob/main/clerk/tokens_issuer.go">Token Issuer</a>
 */
public class TokenIssuer {
    private final String iss;
    private boolean isSatellite;
    private String proxyURL;

    /**
     * Constructs a TokenIssuer with a specified issuer URL.
     *
     * @param iss The issuer URL.
     */
    public TokenIssuer(String iss) {
        this.iss = iss;
    }

    /**
     * Sets the satellite domain flag for this issuer.
     *
     * @param isSatellite Flag indicating whether the issuer is a satellite domain.
     * @return The current instance of TokenIssuer for method chaining.
     */
    public TokenIssuer withSatelliteDomain(boolean isSatellite) {
        this.isSatellite = isSatellite;
        return this;
    }

    /**
     * Sets the proxy URL for this issuer.
     *
     * @param proxyURL The proxy URL to set.
     * @return The current instance of TokenIssuer for method chaining.
     */
    public TokenIssuer withProxyURL(String proxyURL) {
        this.proxyURL = proxyURL;
        return this;
    }

    /**
     * Validates the issuer based on the set criteria (satellite domain, proxy URL, or default rules).
     *
     * @return true if the issuer is valid based on the provided criteria, false otherwise.
     */
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

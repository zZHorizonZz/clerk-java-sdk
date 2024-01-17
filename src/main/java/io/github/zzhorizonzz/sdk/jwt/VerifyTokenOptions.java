package io.github.zzhorizonzz.sdk.jwt;

import org.jose4j.jwk.JsonWebKey;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class VerifyTokenOptions {
    private Map<String, Void> authorizedParties;
    private long leeway; // Time in milliseconds
    private JsonWebKey jwk;
    private boolean isSatellite;
    private String proxyURL;
    private Map<String, Object> customClaims;

    public void apply(VerifyTokenOptions options) {
        if (options == null) {
            return;
        }

        if (options.getAuthorizedParties() != null) {
            this.authorizedParties = options.getAuthorizedParties();
        }

        if (options.getLeeway() != 0) {
            this.leeway = options.getLeeway();
        }

        if (options.getJwk() != null) {
            this.jwk = options.getJwk();
        }

        if (options.isSatellite()) {
            this.isSatellite = options.isSatellite();
        }

        if (options.getProxyURL() != null) {
            this.proxyURL = options.getProxyURL();
        }
    }

    public Map<String, Void> getAuthorizedParties() {
        return authorizedParties;
    }

    public void setAuthorizedParties(Map<String, Void> authorizedParties) {
        this.authorizedParties = authorizedParties;
    }

    public long getLeeway() {
        return leeway;
    }

    public void setLeeway(long leeway, TimeUnit unit) {
        this.leeway = TimeUnit.MILLISECONDS.convert(leeway, unit);
    }

    public void setLeeway(long leeway) {
        this.leeway = leeway;
    }

    public JsonWebKey getJwk() {
        return jwk;
    }

    public void setJwk(JsonWebKey jwk) {
        this.jwk = jwk;
    }

    public boolean isSatellite() {
        return isSatellite;
    }

    public void setSatellite(boolean isSatellite) {
        this.isSatellite = isSatellite;
    }

    public String getProxyURL() {
        return proxyURL;
    }

    public void setProxyURL(String proxyURL) {
        this.proxyURL = proxyURL;
    }

    public Map<String, Object> getCustomClaims() {
        return customClaims;
    }

    public void setCustomClaims(Map<String, Object> customClaims) {
        this.customClaims = customClaims;
    }
}

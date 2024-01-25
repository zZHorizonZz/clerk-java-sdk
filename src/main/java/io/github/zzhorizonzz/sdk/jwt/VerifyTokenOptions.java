package io.github.zzhorizonzz.sdk.jwt;

import lombok.Data;
import org.jose4j.jwk.JsonWebKey;

import java.util.Map;

/**
 * Holds options for verifying a JWT (JSON Web Token).
 * This class includes various settings such as authorized parties, time leeway,
 * JsonWebKey for verification, and custom configuration for satellite domains and proxy URLs.
 */
@Data
public class VerifyTokenOptions {
    private Map<String, Void> authorizedParties;
    private long leeway; // Time in milliseconds
    private JsonWebKey jwk;
    private boolean isSatellite;
    private String proxyURL;
    private Map<String, Object> customClaims;

    /**
     * Applies the settings from another VerifyTokenOptions instance to this instance.
     * This method selectively updates the fields if they are set in the provided options object.
     *
     * @param options The VerifyTokenOptions instance from which to copy the settings.
     */
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
}

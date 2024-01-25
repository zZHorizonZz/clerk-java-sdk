package io.github.zzhorizonzz.sdk.session;

import lombok.Data;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;

import java.util.Arrays;

/**
 * Represents session-related claims extracted from a JWT (JSON Web Token).
 * This class includes various properties such as session ID, authorized party, and organization-related information.
 */
@Data
public class SessionClaims {
    private JwtClaims claims;
    private String sessionId;
    private String authorizedParty;
    private String activeOrganizationId;
    private String activeOrganizationSlug;
    private String activeOrganizationRole;
    private String[] activeOrganizationPermissions;
    private String rawJson;

    /**
     * Constructs SessionClaims from a set of JWT claims.
     *
     * @param claims The JWT claims from which session-related information will be extracted.
     */
    public SessionClaims(JwtClaims claims) {
        this.claims = claims;
        this.rawJson = claims.getRawJson();

        try {
            extractClaims(claims);
        } catch (MalformedClaimException e) {
            throw new RuntimeException("Error extracting session claims", e);
        }
    }

    /**
     * Extracts session-related claims from the given JWT claims.
     *
     * @param claims The JWT claims to extract information from.
     * @throws MalformedClaimException If a claim is malformed.
     */
    private void extractClaims(JwtClaims claims) throws MalformedClaimException {
        if (claims.hasClaim("session_id")) {
            this.sessionId = claims.getStringClaimValue("sid");
        }

        if (claims.hasClaim("authorized_party")) {
            this.authorizedParty = claims.getStringClaimValue("azp");
        }

        if (claims.hasClaim("active_organization_slug")) {
            this.activeOrganizationSlug = claims.getStringClaimValue("org_slug");
        }

        if (claims.hasClaim("active_organization_role")) {
            this.activeOrganizationRole = claims.getStringClaimValue("org_role");
        }

        if (claims.hasClaim("active_organization_permissions")) {
            this.activeOrganizationPermissions = claims.getStringListClaimValue("active_organization_permissions")
                    .toArray(new String[0]);
        }
    }

    @Override
    public String toString() {
        return "SessionClaims{" +
                "claims=" + claims +
                ", sessionId='" + sessionId + '\'' +
                ", authorizedParty='" + authorizedParty + '\'' +
                ", activeOrganizationId='" + activeOrganizationId + '\'' +
                ", activeOrganizationSlug='" + activeOrganizationSlug + '\'' +
                ", activeOrganizationRole='" + activeOrganizationRole + '\'' +
                ", activeOrganizationPermissions=" + Arrays.toString(activeOrganizationPermissions) +
                ", rawJson='" + rawJson + '\'' +
                '}';
    }
}

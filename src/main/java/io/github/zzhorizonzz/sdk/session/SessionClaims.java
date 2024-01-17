package io.github.zzhorizonzz.sdk.session;

import lombok.Data;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;

import java.util.Arrays;

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

    public SessionClaims(JwtClaims claims) {
        this.claims = claims;
        this.rawJson = claims.getRawJson();

        try {
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
        } catch (MalformedClaimException e) {
            throw new RuntimeException(e);
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

package io.github.zzhorizonzz.sdk.jwt;

import lombok.Data;
import org.jose4j.jwt.JwtClaims;

import java.util.Map;

/**
 * Represents the claims extracted from a JSON Web Token (JWT).
 * This class encapsulates both the standard JWT claims as defined in the JWT specification
 * and any additional custom claims that may be present in the token.
 */
@Data
public class TokenClaims {
    /**
     * The standard JWT claims.
     */
    private JwtClaims standardClaims;

    /**
     * Any extra claims that are not part of the standard JWT claims set.
     */
    private Map<String, Object> extra;


    public TokenClaims(JwtClaims standardClaims, Map<String, Object> extra) {
        this.standardClaims = standardClaims;
        this.extra = extra;
    }
}

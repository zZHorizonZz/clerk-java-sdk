package io.github.zzhorizonzz.sdk.jwt;

import org.jose4j.jwt.JwtClaims;

import java.util.Map;

public class TokenClaims {
    private JwtClaims standardClaims;
    private Map<String, Object> extra;

    public TokenClaims(JwtClaims standardClaims, Map<String, Object> extra) {
        this.standardClaims = standardClaims;
        this.extra = extra;
    }

    public JwtClaims getStandardClaims() {
        return standardClaims;
    }

    public void setStandardClaims(JwtClaims standardClaims) {
        this.standardClaims = standardClaims;
    }

    public Map<String, Object> getExtra() {
        return extra;
    }

    public void setExtra(Map<String, Object> extra) {
        this.extra = extra;
    }
}

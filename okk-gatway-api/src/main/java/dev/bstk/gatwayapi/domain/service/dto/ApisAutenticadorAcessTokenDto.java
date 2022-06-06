package dev.bstk.gatwayapi.domain.service.dto;

import javax.json.bind.annotation.JsonbProperty;
import java.io.Serializable;

public class ApisAutenticadorAcessTokenDto implements Serializable {

    @JsonbProperty("access_token")
    private String accessToken;

    @JsonbProperty("expires_in")
    private String expiresIn;

    @JsonbProperty("scope")
    private String scope;

    @JsonbProperty("token_type")
    private String tokenType;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}

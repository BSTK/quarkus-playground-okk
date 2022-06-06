package dev.bstk.gatwayapi.resource.request;

import javax.json.bind.annotation.JsonbProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

public class ConsultaApiRequest implements Serializable {

    @NotBlank
    @JsonbProperty("app_cliente")
    private String appCliente;

    @NotEmpty
    @JsonbProperty("apis")
    private List<ConsultaApiItemRequest> apis;

    @JsonbProperty("api_obter_token")
    private ConsultaApiTokenRequest apiObterToken;

    public String getAppCliente() {
        return appCliente;
    }

    public void setAppCliente(String appCliente) {
        this.appCliente = appCliente;
    }

    public List<ConsultaApiItemRequest> getApis() {
        return apis;
    }

    public void setApis(List<ConsultaApiItemRequest> apis) {
        this.apis = apis;
    }

    public ConsultaApiTokenRequest getApiObterToken() {
        return apiObterToken;
    }

    public void setApiObterToken(ConsultaApiTokenRequest apiObterToken) {
        this.apiObterToken = apiObterToken;
    }
}

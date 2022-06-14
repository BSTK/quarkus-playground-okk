package dev.bstk.exportadorapipdf.domain.gateway.request;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Schema(description = "Representação dos dados de request")
public class ConsultaApiRequest implements Serializable {

    @NotBlank
    @Schema(name = "app_cliente")
    @JsonbProperty("app_cliente")
    private String appCliente;

    @NotEmpty
    @Schema(name = "apis")
    @JsonbProperty("apis")
    private List<ConsultaApiItemRequest> apis;

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
}

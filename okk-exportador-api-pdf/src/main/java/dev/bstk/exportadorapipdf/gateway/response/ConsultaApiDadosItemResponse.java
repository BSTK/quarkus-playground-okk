package dev.bstk.exportadorapipdf.gateway.response;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Schema(description = "Representação dos dados de response")
public class ConsultaApiDadosItemResponse implements Serializable {

    @NotBlank
    @Schema(name = "nome_api_externa")
    @JsonbProperty("nome_api_externa")
    private String nomeApiExterna;

    @NotNull
    @Schema(name = "response")
    @JsonbProperty("response")
    private Object response;

    public String getNomeApiExterna() {
        return nomeApiExterna;
    }

    public void setNomeApiExterna(String nomeApiExterna) {
        this.nomeApiExterna = nomeApiExterna;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }
}

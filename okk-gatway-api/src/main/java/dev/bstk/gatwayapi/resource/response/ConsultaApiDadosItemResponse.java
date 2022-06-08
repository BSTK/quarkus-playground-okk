package dev.bstk.gatwayapi.resource.response;

import javax.json.bind.annotation.JsonbProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class ConsultaApiDadosItemResponse implements Serializable {

    @NotBlank
    @JsonbProperty("nome_api_externa")
    private final String nomeApiExterna;

    @NotNull
    @JsonbProperty("response")
    private Object response;

    public ConsultaApiDadosItemResponse(final String nomeApiExterna) {
        this.nomeApiExterna = nomeApiExterna;
    }

    public String getNomeApiExterna() {
        return nomeApiExterna;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }
}

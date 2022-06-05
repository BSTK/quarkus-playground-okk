package dev.bstk.gatwayapi.resource.response;

import org.hibernate.validator.constraints.URL;

import javax.json.bind.annotation.JsonbProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class ConsultaApiDadosItemResponse implements Serializable {

    @NotBlank
    @JsonbProperty("nome_api_externa")
    private final String nomeApiExterna;

    @URL
    @NotBlank
    @JsonbProperty("url")
    private final String url;

    @NotNull
    @JsonbProperty("response")
    private Object response;

    public ConsultaApiDadosItemResponse(final String nomeApiExterna,
                                        final String url) {
        this.nomeApiExterna = nomeApiExterna;
        this.url = url;
    }

    public String getNomeApiExterna() {
        return nomeApiExterna;
    }

    public String getUrl() {
        return url;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }
}

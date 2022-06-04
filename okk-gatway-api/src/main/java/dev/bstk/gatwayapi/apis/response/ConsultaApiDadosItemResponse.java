package dev.bstk.gatwayapi.apis.response;

import org.hibernate.validator.constraints.URL;

import javax.json.bind.annotation.JsonbProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class ConsultaApiDadosItemResponse implements Serializable {

    @NotBlank
    @JsonbProperty("nome_api_externa")
    private String nomeApiExterna;

    @URL
    @NotBlank
    @JsonbProperty("url")
    private String url;

    @NotNull
    @JsonbProperty("response")
    private Object response;

    public String getNomeApiExterna() {
        return nomeApiExterna;
    }

    public void setNomeApiExterna(String nomeApiExterna) {
        this.nomeApiExterna = nomeApiExterna;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }
}

package dev.bstk.gatwayapi.apis.request;

import org.hibernate.validator.constraints.URL;

import javax.json.bind.annotation.JsonbProperty;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class ConsultaApiItemRequest implements Serializable {

    @NotBlank
    @JsonbProperty("nome_api_externa")
    private String nomeApiExterna;

    @URL
    @NotBlank
    @JsonbProperty("url")
    private String url;

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
}

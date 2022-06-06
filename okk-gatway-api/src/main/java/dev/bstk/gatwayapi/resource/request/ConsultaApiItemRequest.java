package dev.bstk.gatwayapi.resource.request;

import org.hibernate.validator.constraints.URL;

import javax.json.bind.annotation.JsonbProperty;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Map;

public class ConsultaApiItemRequest implements Serializable {

    @NotBlank
    @JsonbProperty("nome_api_externa")
    private String nomeApiExterna;

    @URL
    @NotBlank
    @JsonbProperty("url")
    private String url;

    @JsonbProperty("headers")
    private Map<String, String> headers;

    @JsonbProperty("parametros")
    private Map<String, String> parametros;

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

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public Map<String, String> getParametros() {
        return parametros;
    }

    public void setParametros(Map<String, String> parametros) {
        this.parametros = parametros;
    }
}

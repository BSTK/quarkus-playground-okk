package dev.bstk.gatwayapi.resource.request;

import org.hibernate.validator.constraints.URL;

import javax.json.bind.annotation.JsonbProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Map;

public class ConsultaApiTokenRequest implements Serializable {

    @URL
    @NotBlank
    @JsonbProperty("url")
    private String url;

    @NotNull
    @JsonbProperty("payload")
    private Object payload;

    @JsonbProperty("headers")
    private Map<String, String> headers;

    @JsonbProperty("parametros")
    private Map<String, String> parametros;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
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

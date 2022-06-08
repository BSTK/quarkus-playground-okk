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

    @JsonbProperty("query_params")
    private Map<String, String> queryParams;

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

    public Map<String, String> getQueryParams() {
        return queryParams;
    }

    public void setQueryParams(Map<String, String> queryParams) {
        this.queryParams = queryParams;
    }
}

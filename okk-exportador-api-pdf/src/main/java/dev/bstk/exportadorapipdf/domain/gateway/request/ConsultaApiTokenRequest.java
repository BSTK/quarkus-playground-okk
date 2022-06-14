package dev.bstk.exportadorapipdf.domain.gateway.request;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.validator.constraints.URL;

import javax.json.bind.annotation.JsonbProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Map;

@Schema(description = "Representação dos dados de request para obtenção do Token de autenticação")
public class ConsultaApiTokenRequest implements Serializable {

    @URL
    @NotBlank
    @Schema(name = "url")
    @JsonbProperty("url")
    private String url;

    @NotNull
    @Schema(name = "payload")
    @JsonbProperty("payload")
    private Object payload;

    @Schema(name = "headers")
    @JsonbProperty("headers")
    private Map<String, String> headers;

    @Schema(name = "query_params")
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

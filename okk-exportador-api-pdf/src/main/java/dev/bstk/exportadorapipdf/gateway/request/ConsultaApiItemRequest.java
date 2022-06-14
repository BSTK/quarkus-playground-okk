package dev.bstk.exportadorapipdf.gateway.request;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.validator.constraints.URL;

import javax.json.bind.annotation.JsonbProperty;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Map;

@Schema(description = "Representação dos dados de request")
public class ConsultaApiItemRequest implements Serializable {

    @NotBlank
    @Schema(name = "nome_api_externa")
    @JsonbProperty("nome_api_externa")
    private String nomeApiExterna;

    @URL
    @NotBlank
    @Schema(name = "url")
    @JsonbProperty("url")
    private String url;

    @Schema(name = "headers")
    @JsonbProperty("headers")
    private Map<String, String> headers;

    @Schema(name = "query_params")
    @JsonbProperty("query_params")
    private Map<String, String> queryParams;

    @Schema(name = "api_obter_token")
    @JsonbProperty("api_obter_token")
    private ConsultaApiTokenRequest apiObterToken;

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

    public Map<String, String> getQueryParams() {
        return queryParams;
    }

    public void setQueryParams(Map<String, String> queryParams) {
        this.queryParams = queryParams;
    }

    public ConsultaApiTokenRequest getApiObterToken() {
        return apiObterToken;
    }

    public void setApiObterToken(ConsultaApiTokenRequest apiObterToken) {
        this.apiObterToken = apiObterToken;
    }
}

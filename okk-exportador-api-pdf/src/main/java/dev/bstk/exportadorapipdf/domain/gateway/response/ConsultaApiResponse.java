package dev.bstk.exportadorapipdf.domain.gateway.response;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;
import java.io.Serializable;
import java.util.List;

@Schema(description = "Representação dos dados de response")
public class ConsultaApiResponse implements Serializable {

    @Schema(name = "data_hora_request")
    @JsonbProperty("data_hora_request")
    private String dataHoraRequest;

    @Schema(name = "dados")
    @JsonbProperty("dados")
    private List<ConsultaApiDadosItemResponse> dados;

    public String getDataHoraRequest() {
        return dataHoraRequest;
    }

    public void setDataHoraRequest(String dataHoraRequest) {
        this.dataHoraRequest = dataHoraRequest;
    }

    public List<ConsultaApiDadosItemResponse> getDados() {
        return dados;
    }

    public void setDados(List<ConsultaApiDadosItemResponse> dados) {
        this.dados = dados;
    }
}

package dev.bstk.gatwayapi.apis.response;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.json.bind.annotation.JsonbProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class ConsultaApiResponse implements Serializable {

    @JsonbProperty("data_hora_request")
    @JsonbDateFormat("dd-MM-yyyy hh:mm:ss:sss")
    private LocalDateTime dataHoraRequest;

    @JsonbProperty("dados")
    private List<ConsultaApiDadosItemResponse> dados;

    public LocalDateTime getDataHoraRequest() {
        return dataHoraRequest;
    }

    public void setDataHoraRequest(LocalDateTime dataHoraRequest) {
        this.dataHoraRequest = dataHoraRequest;
    }

    public List<ConsultaApiDadosItemResponse> getDados() {
        return dados;
    }

    public void setDados(List<ConsultaApiDadosItemResponse> dados) {
        this.dados = dados;
    }
}

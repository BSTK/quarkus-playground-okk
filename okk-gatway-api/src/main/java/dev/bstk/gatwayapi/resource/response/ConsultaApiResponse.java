package dev.bstk.gatwayapi.resource.response;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.json.bind.annotation.JsonbProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class ConsultaApiResponse implements Serializable {

    @JsonbProperty("data_hora_request")
    @JsonbDateFormat("dd-MM-yyyy hh:mm:ss:sss")
    private final LocalDateTime dataHoraRequest;

    @JsonbProperty("dados")
    private final List<ConsultaApiDadosItemResponse> dados;

    public ConsultaApiResponse(final List<ConsultaApiDadosItemResponse> dados) {
        this.dataHoraRequest = LocalDateTime.now();
        this.dados = dados;
    }

    public LocalDateTime getDataHoraRequest() {
        return dataHoraRequest;
    }

    public List<ConsultaApiDadosItemResponse> getDados() {
        return dados;
    }
}

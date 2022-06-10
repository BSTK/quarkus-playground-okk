package dev.bstk.gatwayapi.resource.response;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.json.bind.annotation.JsonbProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "Representação dos dados de response")
public class ConsultaApiResponse implements Serializable {

    @Schema(name = "data_hora_request")
    @JsonbProperty("data_hora_request")
    @JsonbDateFormat("dd-MM-yyyy hh:mm:ss")
    private final LocalDateTime dataHoraRequest;

    @Schema(name = "dados")
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

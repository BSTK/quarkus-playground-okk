package dev.bstk.exportadorapipdf.domain.parser;

import dev.bstk.exportadorapipdf.domain.gateway.request.ConsultaApiRequest;

public final class ConsultarDadosApiGatewayRequestParser {

    private ConsultarDadosApiGatewayRequestParser() { }


    public static ConsultaApiRequest request() {
        return new ConsultaApiRequest();
    }
}

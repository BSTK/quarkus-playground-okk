package dev.bstk.exportadorapipdf.domain.service;

import dev.bstk.exportadorapipdf.domain.gateway.ConsultaDadosGatewayApi;
import dev.bstk.exportadorapipdf.domain.gateway.request.ConsultaApiRequest;
import dev.bstk.exportadorapipdf.domain.gateway.response.ConsultaApiResponse;
import dev.bstk.exportadorapipdf.domain.parser.ConsultarDadosApiGatewayRequestParser;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Objects;

@ApplicationScoped
public class ConsultarDadosApiGatewayService {

    @Inject
    @RestClient
    protected ConsultaDadosGatewayApi consultaDadosGatewayApi;

    @Inject
    protected ConsultarDadosApiGatewayRequestParser requestParser;


    /// TODO: OBTEM OS DADOS DA API GATEWAY
    /// TODO: CASO SUCESSO, INSERIR NAS TABELAS PARA SEREM EXPORTADOS EM PDF
    /// TODO: CASO ERRO, INSERIR NA TABELA DE DADOS PARA SEREM REPROCESSADOS
    public void consultarDados() {
        final ConsultaApiRequest request = requestParser.request();
        final ConsultaApiResponse response = consultaDadosGatewayApi.apis(request);

        if (Objects.isNull(response)) {
            throw new IllegalStateException("Response inválida! Reprocessar!");
        }
    }
}

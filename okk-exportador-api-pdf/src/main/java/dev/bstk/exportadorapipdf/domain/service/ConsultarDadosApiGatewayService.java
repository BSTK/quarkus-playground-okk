package dev.bstk.exportadorapipdf.domain.service;

import dev.bstk.exportadorapipdf.domain.parser.ConsultarDadosApiGatewayRequestParser;
import dev.bstk.exportadorapipdf.domain.parser.impl.GeniusEndpointSearchConteudoPdfParserImpl;
import dev.bstk.exportadorapipdf.domain.model.genius.GeniusEndpointSearchConteudoPdf;
import dev.bstk.exportadorapipdf.gateway.ConsultaDadosGatewayApi;
import dev.bstk.exportadorapipdf.gateway.request.ConsultaApiRequest;
import dev.bstk.exportadorapipdf.gateway.response.ConsultaApiResponse;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ConsultarDadosApiGatewayService {

    @Inject
    @RestClient
    protected ConsultaDadosGatewayApi consultaDadosGatewayApi;

    @Inject
    protected ConsultarDadosApiGatewayRequestParser requestParser;

    @Inject
    protected GeniusEndpointSearchConteudoPdfParserImpl conteudoPdfParser;


    /// TODO: CASO ERRO, INSERIR NA TABELA DE DADOS PARA SEREM REPROCESSADOS
    public void consultarDados() {
        final ConsultaApiRequest request = requestParser.request();
        final ConsultaApiResponse response = consultaDadosGatewayApi.apis(request);
        final GeniusEndpointSearchConteudoPdf conteudoPdf = conteudoPdfParser.pdf(response);

        /// TODO: CASO SUCESSO, INSERIR NAS TABELAS PARA SEREM EXPORTADOS EM PDF
        /// TODO: SALVAR DADOS: conteudoPdf
        System.out.println("Salvando dados: " + conteudoPdf.toString());
    }
}

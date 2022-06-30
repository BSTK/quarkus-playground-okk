package dev.bstk.exportadorapipdf.domain.service;

import dev.bstk.exportadorapipdf.domain.model.ConteudoPdf;
import dev.bstk.exportadorapipdf.domain.model.genius.GeniusEndpointSearchConteudoPdf;
import dev.bstk.exportadorapipdf.domain.model.genius.GeniusEndpointSearchConteudoPdfRepository;
import dev.bstk.exportadorapipdf.domain.parser.ConsultarDadosApiGatewayRequestParser;
import dev.bstk.exportadorapipdf.domain.parser.impl.GeniusEndpointSearchConteudoPdfParserImpl;
import dev.bstk.exportadorapipdf.gateway.ConsultaDadosGatewayApi;
import dev.bstk.exportadorapipdf.gateway.request.ConsultaApiRequest;
import dev.bstk.exportadorapipdf.gateway.response.ConsultaApiResponse;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class ConsultarDadosApiGatewayService {

    @Inject
    @RestClient
    protected ConsultaDadosGatewayApi consultaDadosGatewayApi;

    @Inject
    protected ConsultarDadosApiGatewayRequestParser requestParser;

    /// TODO: REFATORAR PARA FICAR GENÉRICO
    @Inject
    protected GeniusEndpointSearchConteudoPdfParserImpl conteudoPdfParser;

    /// TODO: REFATORAR PARA FICAR GENÉRICO
    @Inject
    protected GeniusEndpointSearchConteudoPdfRepository geniusEndpointSearchConteudoPdfRepository;


    /// TODO: CASO ERRO, INSERIR NA TABELA DE DADOS PARA SEREM REPROCESSADOS
    @Transactional
    public void consultarDados() {
        final ConsultaApiRequest request = requestParser.request();
        final ConsultaApiResponse response = consultaDadosGatewayApi.apis(request);

        /// TODO: REFATORAR PARA FICAR GENÉRICO
        final GeniusEndpointSearchConteudoPdf geniusEndpointSearchConteudoPdf = conteudoPdfParser.pdf(response);

        final ConteudoPdf conteudoPdf = new ConteudoPdf();
        conteudoPdf.setDados(geniusEndpointSearchConteudoPdf);

        geniusEndpointSearchConteudoPdfRepository.persist(conteudoPdf);
    }
}

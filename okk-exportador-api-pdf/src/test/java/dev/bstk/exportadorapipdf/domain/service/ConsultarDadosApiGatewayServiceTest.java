package dev.bstk.exportadorapipdf.domain.service;

import dev.bstk.exportadorapipdf.domain.parser.impl.GeniusEndpointSearchConteudoPdfParserImpl;
import dev.bstk.exportadorapipdf.domain.parser.ConsultarDadosApiGatewayRequestParser;
import dev.bstk.exportadorapipdf.domain.model.genius.GeniusEndpointSearchConteudoPdf;
import dev.bstk.exportadorapipdf.gateway.ConsultaDadosGatewayApi;
import dev.bstk.exportadorapipdf.gateway.request.ConsultaApiRequest;
import dev.bstk.exportadorapipdf.gateway.response.ConsultaApiResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConsultarDadosApiGatewayServiceTest {

    @InjectMocks
    private ConsultarDadosApiGatewayService consultarDadosApiGatewayService;

    @Mock
    protected ConsultaDadosGatewayApi consultaDadosGatewayApi;

    @Mock
    protected ConsultarDadosApiGatewayRequestParser requestParser;

    @Mock
    protected GeniusEndpointSearchConteudoPdfParserImpl conteudoPdfParser;


    @Test
    @DisplayName("Deve consultar dados das apis no gateway de api")
    void deveConsultarDadosDasApisNoGatewayDeApi() {
        when(requestParser.request()).thenReturn(new ConsultaApiRequest());
        when(consultaDadosGatewayApi.apis(any(ConsultaApiRequest.class))).thenReturn(new ConsultaApiResponse());
        when(conteudoPdfParser.pdf(any(ConsultaApiResponse.class))).thenReturn(new GeniusEndpointSearchConteudoPdf());

        consultarDadosApiGatewayService.consultarDados();

        verify(requestParser).request();
        verify(consultaDadosGatewayApi).apis(any(ConsultaApiRequest.class));
        verify(conteudoPdfParser).pdf(any(ConsultaApiResponse.class));
    }
}

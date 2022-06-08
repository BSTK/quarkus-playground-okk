package dev.bstk.gatwayapi.domain.service;

import dev.bstk.gatwayapi.domain.service.dto.ApisAutenticadorAcessTokenDto;
import dev.bstk.gatwayapi.resource.request.ConsultaApiItemRequest;
import dev.bstk.gatwayapi.resource.request.ConsultaApiRequest;
import dev.bstk.gatwayapi.resource.request.ConsultaApiTokenRequest;
import dev.bstk.gatwayapi.resource.response.ConsultaApiResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class ApisServiceTest extends JaxRsHttpClient {

    @InjectMocks
    private ApisService apisService;

    @Mock
    private ApisAutenticadorService autenticadorService;

    @Test
    @DisplayName("Deve retornar dados consulta api para um caso de [ SUCESSO OK ]")
    void deveRetornarDadosConsultaApiParaUmCasoDeSucessoOk() {
        execute(
            Response
                .ok("OBJETO_CONTENDO_SUCESSO_OK")
                .status(Response.Status.OK)
                .build(),
            "OBJETO_CONTENDO_SUCESSO_OK"
        );
    }

    @Test
    @DisplayName("Deve retornar dados consulta api para um caso de [ CLIENT ERROR ]")
    void deveRetornarDadosConsultaApiParaUmCasoDeClientError() {
        execute(
            Response.status(Response.Status.BAD_REQUEST).build(),
            "OBJETO_CONTENDO_ERRO_CLIENTE"
        );
    }

    @Test
    @DisplayName("Deve retornar dados consulta api para um caso de [ SERVER ERROR ]")
    void deveRetornarDadosConsultaDeApiParaUmCasoDeServerError() {
        execute(
            Response.status(Response.Status.INTERNAL_SERVER_ERROR).build(),
            "OBJETO_CONTENDO_ERRO_SERVIDOR"
        );
    }

    @Test
    @MockitoSettings(strictness = Strictness.LENIENT)
    @DisplayName("Deve retornar dados consulta api para um caso de [ SUCESSO OK ] com autenticação")
    void deveRetornarDadosConsultaApiParaUmCasoDeSucessoOkComAutenticacao() {
        mockResponse(
            Response
            .ok("OBJETO_CONTENDO_SUCESSO_OK")
            .status(Response.Status.OK)
            .build());

        final ApisAutenticadorAcessTokenDto acessToken = new ApisAutenticadorAcessTokenDto();
        acessToken.setAccessToken("ACESS_TOKEN_MOCK");
        Mockito
            .when(autenticadorService.obterToken(any(ConsultaApiTokenRequest.class)))
            .thenReturn(acessToken);

        final ConsultaApiRequest request = requestComAutenticacao();
        final ConsultaApiResponse response = apisService.consultar(request);

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getDados());
        Assertions.assertNotNull(response.getDataHoraRequest());

        Assertions.assertFalse(response.getDados().isEmpty());

        Assertions.assertEquals(response.getDados().size(), request.getApis().size());

        response
            .getDados()
            .forEach(item -> {
                Assertions.assertInstanceOf(String.class, item.getResponse());
                Assertions.assertEquals("OBJETO_CONTENDO_SUCESSO_OK", item.getResponse());
            });

        final int quantidadeHeaders = request.getApis().get(0).getHeaders().size();
        Mockito.verify(builder).header(HttpHeaders.AUTHORIZATION, acessToken.getAccessToken());
        Mockito.verify(builder, Mockito.times(quantidadeHeaders)).header(anyString(), anyString());
    }

    @Test
    @MockitoSettings(strictness = Strictness.LENIENT)
    @DisplayName("Deve retornar dados consulta api para um caso de [ SUCESSO OK ] com autenticação")
    void deveRetornarDadosConsultaApiParaUmCasoDeSucessoOkComAutenticacaoSoComHeaderDeAutorizasao() {
        mockResponse(
            Response
                .ok("OBJETO_CONTENDO_SUCESSO_OK")
                .status(Response.Status.OK)
                .build());

        final ApisAutenticadorAcessTokenDto acessToken = new ApisAutenticadorAcessTokenDto();
        acessToken.setAccessToken("ACESS_TOKEN_MOCK");
        Mockito
            .when(autenticadorService.obterToken(any(ConsultaApiTokenRequest.class)))
            .thenReturn(acessToken);

        final ConsultaApiRequest request = requestComAutenticacao();
        request.getApis().get(0).setHeaders(null);

        final ConsultaApiResponse response = apisService.consultar(request);

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getDados());
        Assertions.assertNotNull(response.getDataHoraRequest());

        Assertions.assertFalse(response.getDados().isEmpty());

        Assertions.assertEquals(response.getDados().size(), request.getApis().size());

        response
            .getDados()
            .forEach(item -> {
                Assertions.assertInstanceOf(String.class, item.getResponse());
                Assertions.assertEquals("OBJETO_CONTENDO_SUCESSO_OK", item.getResponse());
            });

        Mockito.verify(builder).header(HttpHeaders.AUTHORIZATION, acessToken.getAccessToken());
        Mockito.verify(builder, Mockito.times(1)).header(anyString(), anyString());
    }

    private void execute(final Response httpResponse,
                         final Object httpResponseConteudo) {
        mockResponse(httpResponse);

        final ConsultaApiRequest request = request();
        final ConsultaApiResponse response = apisService.consultar(request);

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getDados());
        Assertions.assertNotNull(response.getDataHoraRequest());

        Assertions.assertFalse(response.getDados().isEmpty());

        Assertions.assertEquals(response.getDados().size(), request.getApis().size());

        response
            .getDados()
            .forEach(item -> {
                Assertions.assertInstanceOf(String.class, item.getResponse());
                Assertions.assertEquals(httpResponseConteudo, item.getResponse());
            });
    }

    private void mockResponse(final Response response) {
        clientBuilderMock
            .when(() -> ClientBuilder
                .newClient()
                .target(anyString()).request(MediaType.APPLICATION_JSON)
                .get())
            .thenReturn(response);
    }

    private ConsultaApiRequest request() {
        final ConsultaApiItemRequest itemRequest = new ConsultaApiItemRequest();
        itemRequest.setUrl("https://mock-ok.com.br");
        itemRequest.setNomeApiExterna("Mock OK");
        itemRequest.setHeaders(Map.of("chave_a", "valor_a"));
        itemRequest.setQueryParams(Map.of("chave_a", "valor_a"));

        final ConsultaApiRequest request = new ConsultaApiRequest();
        request.setApis(Collections.singletonList(itemRequest));
        request.setAppCliente("MOCK_CLIENTE");
        return request;
    }

    private ConsultaApiRequest requestComAutenticacao() {
        final Map<String, String> headersQueryParams = new HashMap<>();
        headersQueryParams.put("chave_a", "valor_a");

        final ConsultaApiTokenRequest apiToken = new ConsultaApiTokenRequest();
        apiToken.setHeaders(headersQueryParams);
        apiToken.setQueryParams(headersQueryParams);
        apiToken.setUrl("https://mock-ok.com.br/auth");
        apiToken.setPayload(new Object());

        final ConsultaApiItemRequest itemRequest = new ConsultaApiItemRequest();
        itemRequest.setUrl("https://mock-ok.com.br");
        itemRequest.setApiObterToken(apiToken);
        itemRequest.setNomeApiExterna("Mock OK");
        itemRequest.setHeaders(headersQueryParams);
        itemRequest.setQueryParams(headersQueryParams);

        final ConsultaApiRequest request = new ConsultaApiRequest();
        request.setApis(Collections.singletonList(itemRequest));
        request.setAppCliente("MOCK_CLIENTE");
        return request;
    }
}

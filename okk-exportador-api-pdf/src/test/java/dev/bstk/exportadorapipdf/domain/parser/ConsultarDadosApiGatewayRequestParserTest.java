package dev.bstk.exportadorapipdf.domain.parser;

import dev.bstk.exportadorapipdf.gateway.request.ConsultaApiItemRequest;
import dev.bstk.exportadorapipdf.gateway.request.ConsultaApiRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ConsultarDadosApiGatewayRequestParserTest {

    @InjectMocks
    protected ConsultarDadosApiGatewayRequestParser requestParser;

    @Mock
    protected ConsultarDadosApiGatewayLeitorArquivosJson leitorArquivosJson;


    @Test
    @DisplayName("Deve retonar dados parseados para uma request válida sem token de autenticação")
    void deveRetonarDadosParseadosParaUmaRequestValidaSemTokenDeAutenticacao() {
        final ConsultaApiRequest requestMock = request();
        when(leitorArquivosJson.parse(ConsultaApiRequest.class))
            .thenReturn(List.of(requestMock, requestMock, requestMock));

        final ConsultaApiRequest request = requestParser.request();

        Assertions.assertNotNull(request);
        Assertions.assertNotNull(request.getApis());
        Assertions.assertFalse(request.getApis().isEmpty());
        Assertions.assertNotNull(request.getAppCliente());

        for (final ConsultaApiItemRequest api : request.getApis()) {
            Assertions.assertNotNull(api.getUrl());
            Assertions.assertNull(api.getApiObterToken());
            Assertions.assertFalse(api.getUrl().isEmpty());
            Assertions.assertFalse(api.getUrl().isBlank());
        }
    }

    @Test
    @DisplayName("Deve retonar dados parseados para uma request válida sem token de autenticação")
    void deveRetonarDadosParseadosParaUmaRequestValidaSemTokenDeAutenticacaoParaUmaUrlComId() {
        final ConsultaApiRequest requestMock = request();
        for (ConsultaApiItemRequest api : requestMock.getApis()) {
            api.setUrl("https:mock-api.com/{ID}");
        }

        when(leitorArquivosJson.parse(ConsultaApiRequest.class))
            .thenReturn(List.of(requestMock, requestMock, requestMock));

        final ConsultaApiRequest request = requestParser.request();

        Assertions.assertNotNull(request);
        Assertions.assertNotNull(request.getApis());
        Assertions.assertFalse(request.getApis().isEmpty());
        Assertions.assertNotNull(request.getAppCliente());

        for (final ConsultaApiItemRequest api : request.getApis()) {
            Assertions.assertNull(api.getApiObterToken());

            Assertions.assertNotNull(api.getUrl());
            Assertions.assertFalse(api.getUrl().isEmpty());
            Assertions.assertFalse(api.getUrl().isBlank());

            final String[] url = api.getUrl().split("/");
            final String urlUltimoPath = url[url.length - 1];

            Assertions.assertTrue(Character.isDigit(urlUltimoPath.charAt(0)));
        }
    }

    private ConsultaApiRequest request() {
        final Map<String, String> queryHeadersParam = Map.of("CHAVE_A", "VALOR_A", "CHAVE_B", "VALOR_B");

        final ConsultaApiItemRequest itemRequest = new ConsultaApiItemRequest();
        itemRequest.setUrl("https:mock-api.com");
        itemRequest.setHeaders(queryHeadersParam);
        itemRequest.setQueryParams(queryHeadersParam);
        itemRequest.setNomeApiExterna("MOCK-API-EXTERNA");

        final ConsultaApiRequest request = new ConsultaApiRequest();
        request.setApis(Collections.singletonList(itemRequest));
        request.setAppCliente("APP_CLIENTE_MOCK");

        return request;
    }
}

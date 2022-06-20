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

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConsultarDadosApiGatewayRequestParserTest {

    @InjectMocks
    protected ConsultarDadosApiGatewayRequestParser requestParser;

    @Mock
    protected ConsultarDadosApiGatewayLeitorArquivosJson leitorArquivosJson;

    @Test
    @DisplayName("Deve retonar dados parseados para uma request válida sem token de autenticação")
    void deveRetonarDadosParseadosParaUmaRequestValidaSemTokenDeAutenticacao() {
        when(leitorArquivosJson.parse(ConsultaApiRequest.class))
            .thenReturn(requests());

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

    private List<ConsultaApiRequest> requests() {
        final Map<String, String> queryHeadersParam = Map.of("CHAVE_A", "VALOR_A", "CHAVE_B", "VALOR_B");

        final ConsultaApiItemRequest itemRequestA = new ConsultaApiItemRequest();
        itemRequestA.setUrl("https:mock-api.com");
        itemRequestA.setHeaders(queryHeadersParam);
        itemRequestA.setQueryParams(queryHeadersParam);
        itemRequestA.setNomeApiExterna("MOCK-API-EXTERNA_A");

        final ConsultaApiRequest requestA = new ConsultaApiRequest();
        requestA.setApis(Collections.singletonList(itemRequestA));
        requestA.setAppCliente("APP_CLIENTE_MOCK_A");

        final ConsultaApiItemRequest itemRequestB = new ConsultaApiItemRequest();
        itemRequestB.setUrl("https:mock-api.com");
        itemRequestB.setHeaders(queryHeadersParam);
        itemRequestB.setQueryParams(queryHeadersParam);
        itemRequestB.setNomeApiExterna("MOCK-API-EXTERNA_A");

        final ConsultaApiRequest requestB = new ConsultaApiRequest();
        requestB.setApis(Collections.singletonList(itemRequestB));
        requestB.setAppCliente("APP_CLIENTE_MOCK_B");

        return List.of(requestA, requestB);
    }
}

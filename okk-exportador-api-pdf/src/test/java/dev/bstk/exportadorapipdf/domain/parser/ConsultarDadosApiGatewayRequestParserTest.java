package dev.bstk.exportadorapipdf.domain.parser;

import dev.bstk.exportadorapipdf.gateway.request.ConsultaApiItemRequest;
import dev.bstk.exportadorapipdf.gateway.request.ConsultaApiRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ConsultarDadosApiGatewayRequestParserTest {

    @InjectMocks
    protected ConsultarDadosApiGatewayRequestParser requestParser;

    @Mock
    protected ConsultarDadosApiGatewayLeitorArquivosJson leitorArquivosJson;


    @BeforeEach
    void tearDown() throws NoSuchFieldException, IllegalAccessException {
        final Field cache = ConsultarDadosApiGatewayRequestParser.class.getDeclaredField("CACHE");
        cache.setAccessible(true);

        final Field camposModificados = Field.class.getDeclaredField("modifiers");
        camposModificados.setAccessible(true);
        camposModificados.setInt(cache, cache.getModifiers() & ~Modifier.FINAL);
        cache.set(null, new ConcurrentHashMap<>());
    }

    @Test
    @DisplayName("Deve retonar dados parseados para uma request válida sem token de autenticação")
    void deveRetonarDadosParseadosParaUmaRequestValidaSemTokenDeAutenticacao() {
        final ConsultaApiRequest requestMock = request();
        when(leitorArquivosJson.parse(ConsultaApiRequest.class))
            .thenReturn(List.of(requestMock, requestMock, requestMock));

        final ConsultaApiRequest request = requestParser.request();
        validarAssertionsPadrao(request);
    }

    @Test
    @DisplayName("Deve retonar dados parseados para uma request válida sem token de autenticação para uma url com [ Id ]")
    void deveRetonarDadosParseadosParaUmaRequestValidaSemTokenDeAutenticacaoParaUmaUrlComId() {
        final ConsultaApiRequest requestMock = request();
        for (ConsultaApiItemRequest api : requestMock.getApis()) {
            api.setUrl("https:mock-api.com/{ID}");
        }

        when(leitorArquivosJson.parse(ConsultaApiRequest.class))
            .thenReturn(List.of(requestMock, requestMock, requestMock));

        final ConsultaApiRequest request = requestParser.request();

        validarAssertionsPadrao(request);

        for (final ConsultaApiItemRequest api : request.getApis()) {
            final String[] url = api.getUrl().split("/");
            final String urlUltimoPath = url[url.length - 1];

            Assertions.assertTrue(Character.isDigit(urlUltimoPath.charAt(0)));
        }
    }

    @Test
    @DisplayName("Deve retonar dados parseados para uma request válida sem token de autenticação paraUma Url com [ Search ]")
    void deveRetonarDadosParseadosParaUmaRequestValidaSemTokenDeAutenticacaoParaUmaUrlComSearch() {
        final ConsultaApiRequest requestMock = request();
        for (ConsultaApiItemRequest api : requestMock.getApis()) {
            api.setUrl("https:mock-api.com/search");
        }

        when(leitorArquivosJson.parse(ConsultaApiRequest.class))
            .thenReturn(List.of(requestMock, requestMock, requestMock));

        final ConsultaApiRequest request = requestParser.request();

        validarAssertionsPadrao(request);
        validarAssertionsPadraoQueryParams(request, "q");
    }

    @Test
    @DisplayName("Deve retonar dados parseados para uma request válida sem token de autenticação paraUma Url com [ Search ]")
    void deveRetonarDadosParseadosParaUmaRequestValidaSemTokenDeAutenticacaoComQueryParamsPage() {
        final ConsultaApiRequest requestMock = request();
        for (ConsultaApiItemRequest api : requestMock.getApis()) {
            api.getQueryParams().put("page", "1");
        }

        when(leitorArquivosJson.parse(ConsultaApiRequest.class))
            .thenReturn(List.of(requestMock, requestMock, requestMock));

        final ConsultaApiRequest request = requestParser.request();

        validarAssertionsPadrao(request);
        validarAssertionsPadraoQueryParams(request, "page");
    }

    @Test
    @DisplayName("Deve retonar dados parseados para uma request válida sem token de autenticação paraUma Url com [ Search ]")
    void deveRetonarDadosParseadosParaUmaRequestValidaSemTokenDeAutenticacaoComQueryParamsPerPage() {
        final ConsultaApiRequest requestMock = request();
        for (ConsultaApiItemRequest api : requestMock.getApis()) {
            api.getQueryParams().put("per_page", "1");
        }

        when(leitorArquivosJson.parse(ConsultaApiRequest.class))
            .thenReturn(List.of(requestMock, requestMock, requestMock));

        final ConsultaApiRequest request = requestParser.request();

        validarAssertionsPadrao(request);
        validarAssertionsPadraoQueryParams(request, "per_page");
    }

    private void validarAssertionsPadrao(final ConsultaApiRequest request) {
        Assertions.assertNotNull(request);
        Assertions.assertNotNull(request.getApis());
        Assertions.assertFalse(request.getApis().isEmpty());
        Assertions.assertNotNull(request.getAppCliente());

        for (final ConsultaApiItemRequest api : request.getApis()) {
            Assertions.assertNull(api.getApiObterToken());
            Assertions.assertNotNull(api.getUrl());
            Assertions.assertFalse(api.getUrl().isEmpty());
            Assertions.assertFalse(api.getUrl().isBlank());
        }
    }

    private void validarAssertionsPadraoQueryParams(final ConsultaApiRequest request,
                                                    final String queryParamChave) {
        for (final ConsultaApiItemRequest api : request.getApis()) {
            Assertions.assertNotNull(api.getQueryParams());
            Assertions.assertFalse(api.getQueryParams().isEmpty());
            Assertions.assertTrue(api.getQueryParams().containsKey(queryParamChave));
        }
    }

    private ConsultaApiRequest request() {
        final Map<String, String> queryHeadersParam = new HashMap<>();
        queryHeadersParam.put("CHAVE_A", "VALOR_A");
        queryHeadersParam.put("CHAVE_B", "VALOR_B");

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

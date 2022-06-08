package dev.bstk.gatwayapi.domain.service;

import dev.bstk.gatwayapi.domain.service.dto.ApisAutenticadorAcessTokenDto;
import dev.bstk.gatwayapi.resource.request.ConsultaApiTokenRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class ApisAutenticadorServiceTest extends JaxRsHttpClient {

    @InjectMocks
    private ApisAutenticadorService autenticadorService;

    @Test
    @DisplayName("Deve obter um token valido")
    void deveObterUmTokenValido() {
        execute(Response.ok(acessToken()).build(), request());

        Mockito.verify(webTarget, Mockito.times(1)).queryParam(anyString(), anyString());
        Mockito.verify(builder, Mockito.times(1)).header(anyString(), anyString());
    }

    @Test
    @DisplayName("Deve obter um token valido passando headers na consulta")
    void deveObterUmTokenValidoPassandoHeadersNaConsulta() {
        ConsultaApiTokenRequest request = request();
        request.setParametros(Collections.emptyMap());

        execute(Response.ok(acessToken()).build(), request);
        Mockito.verify(webTarget, Mockito.never()).queryParam(anyString(), anyString());
        Mockito.verify(builder, Mockito.times(1)).header(anyString(), anyString());
    }

    @Test
    @DisplayName("Deve obter um token valido passando query params na consulta")
    void deveObterUmTokenValidoPassandoQueryParamsNaConsulta() {
        ConsultaApiTokenRequest request = request();
        request.setHeaders(Collections.emptyMap());

        execute(Response.ok(acessToken()).build(), request);
        Mockito.verify(webTarget, Mockito.times(1)).queryParam(anyString(), anyString());
        Mockito.verify(builder, Mockito.never()).header(anyString(), anyString());
    }

    private void execute(final Response httpResponse,
                         final ConsultaApiTokenRequest apiTokenRequest) {
        mockResponse(httpResponse);

        final ApisAutenticadorAcessTokenDto response = autenticadorService.obterToken(apiTokenRequest);

        Assertions.assertNotNull(response);
        Assertions.assertFalse(response.getAccessToken().isEmpty());
        Assertions.assertFalse(response.getTokenType().isEmpty());
        Assertions.assertFalse(response.getExpiresIn().isEmpty());
        Assertions.assertFalse(response.getScope().isEmpty());
    }

    private ConsultaApiTokenRequest request() {
        ConsultaApiTokenRequest apiTokenRequest = new ConsultaApiTokenRequest();
        apiTokenRequest.setUrl("https://mock-api.com/token");
        apiTokenRequest.setHeaders(Map.of("chave", "valor"));
        apiTokenRequest.setParametros(Map.of("chave", "valor"));
        apiTokenRequest.setPayload("{'audience': 'https://mock-api.com'}");

        return apiTokenRequest;
    }

    private void mockResponse(final Response response) {
        clientBuilderMock
            .when(() -> ClientBuilder
                .newClient()
                .target(anyString()).request(MediaType.APPLICATION_JSON)
                .post(any(Entity.class)))
            .thenReturn(response);
    }

    private ApisAutenticadorAcessTokenDto acessToken() {
        final ApisAutenticadorAcessTokenDto acessTokenDto = new ApisAutenticadorAcessTokenDto();
        acessTokenDto.setAccessToken("AccessToken");
        acessTokenDto.setTokenType("TokenType");
        acessTokenDto.setExpiresIn("ExpiresIn");
        acessTokenDto.setScope("Scope");

        return acessTokenDto;
    }
}

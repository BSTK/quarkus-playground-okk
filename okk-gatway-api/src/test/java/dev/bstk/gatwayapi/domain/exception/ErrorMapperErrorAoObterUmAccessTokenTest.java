package dev.bstk.gatwayapi.domain.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

class ErrorMapperErrorAoObterUmAccessTokenTest {

    private final ExceptionHandler.ErrorMapperErrorAoObterUmAccessToken errorMapper =
        new ExceptionHandler.ErrorMapperErrorAoObterUmAccessToken();

    @Test
    @DisplayName("Deve retornar uma response contendo erro de autenticacao")
    void deveRetornarUmaResponseContendoErroDeAutenticacao() {
        final Response response = errorMapper.toResponse(new ErrorAoObterUmAccessTokenException(
            "Mensagem_ErrorAoObterUmAccessTokenException",
            "DADOS_RESPONSE_COM_ERRO_DE_AUTENTICAÇÃO"));

        Assertions.assertNotNull(response);
        Assertions.assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
        Assertions.assertEquals("DADOS_RESPONSE_COM_ERRO_DE_AUTENTICAÇÃO", response.getEntity());
    }
}

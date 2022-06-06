package dev.bstk.gatwayapi.domain.helper;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import javax.ws.rs.core.Response;

@TestMethodOrder(MethodOrderer.MethodName.class)
class HttpStatusHelperTest {

    @Test
    @DisplayName("Deve retonar [ TRUE ] para requisicao com [ STATUS_OK ]")
    void deveRetonarTrueParaRequisicaoComSucessoOK() {
        Assertions.assertTrue(HttpStatusHelper.ok(Response.Status.OK.getStatusCode()));
    }

    @Test
    @DisplayName("Deve retonar [ TRUE ] para requisicao com [ STATUS_NOK ]")
    void deveRetonarTrueParaRequisicaoComSucessoNOK() {
        Assertions.assertTrue(HttpStatusHelper.nok(Response.Status.BAD_REQUEST.getStatusCode()));
        Assertions.assertTrue(HttpStatusHelper.nok(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()));
    }

    @Test
    @DisplayName("Deve retonar [ TRUE ] para requisicao com [ STATUS_INTERNAL_SERVER_ERROR ]")
    void deveRetonarTrueParaRequisicaoComServerErrorInternalServerError() {
        Assertions.assertTrue(HttpStatusHelper.serverError(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()));
    }

    @Test
    @DisplayName("Deve retonar [ TRUE ] para requisicao com [ STATUS_BAD_REQUEST ]")
    void deveRetonarTrueParaRequisicaoComClientErrorBadRequest() {
        Assertions.assertTrue(HttpStatusHelper.clientError(Response.Status.BAD_REQUEST.getStatusCode()));
    }

    @ParameterizedTest
    @EnumSource(
        value = Response.Status.class,
        names = "OK",
        mode = EnumSource.Mode.EXCLUDE
    )
    @DisplayName("Deve executar bateria de testes [ FALSE ] para [ STATUS_OK ]")
    void deveExecutarBateriaDeTestesFalseParaRequisicaoComSucessoOK(final Response.Status status) {
        Assertions.assertFalse(HttpStatusHelper.ok(status.getStatusCode()));
    }

    @ParameterizedTest
    @EnumSource(
        value = Response.Status.class,
        names = { "BAD_REQUEST", "INTERNAL_SERVER_ERROR" },
        mode = EnumSource.Mode.EXCLUDE
    )
    @DisplayName("Deve executar bateria de testes [ FALSE ] para [ STATUS_NOK ]")
    void deveExecutarBateriaDeTestesFalseParaRequisicaoComSucessoNOK(final Response.Status status) {
        Assertions.assertFalse(HttpStatusHelper.nok(status.getStatusCode()));
    }

    @ParameterizedTest
    @EnumSource(
        value = Response.Status.class,
        names = "BAD_REQUEST",
        mode = EnumSource.Mode.EXCLUDE
    )
    @DisplayName("Deve executar bateria de testes [ FALSE ] para [ STATUS_BAD_REQUEST ]")
    void deveExecutarBateriaDeTestesFalseParaRequisicaoComClientErrorBadRequest(final Response.Status status) {
        Assertions.assertFalse(HttpStatusHelper.clientError(status.getStatusCode()));
    }

    @ParameterizedTest
    @EnumSource(
        value = Response.Status.class,
        names = "INTERNAL_SERVER_ERROR",
        mode = EnumSource.Mode.EXCLUDE
    )
    @DisplayName("Deve executar bateria de testes [ FALSE ] para [ STATUS_INTERNAL_SERVER_ERROR ]")
    void deveExecutarBateriaDeTestesFalseParaRequisicaoComInternalServerError(final Response.Status status) {
        Assertions.assertFalse(HttpStatusHelper.serverError(status.getStatusCode()));
    }
}

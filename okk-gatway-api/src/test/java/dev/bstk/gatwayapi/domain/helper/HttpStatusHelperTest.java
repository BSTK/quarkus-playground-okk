package dev.bstk.gatwayapi.domain.helper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import javax.ws.rs.core.Response;

class HttpStatusHelperTest {

    @Test
    void deveRetonarTrueParaRequisicaoComSucessoOK() {
        Assertions.assertTrue(HttpStatusHelper.ok(Response.Status.OK.getStatusCode()));
    }

    @Test
    void deveRetonarTrueParaRequisicaoComServerErrorInternalServerError() {
        Assertions.assertTrue(HttpStatusHelper.serverError(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()));
    }

    @Test
    void deveRetonarTrueParaRequisicaoComClientErrorBadRequest() {
        Assertions.assertTrue(HttpStatusHelper.clientError(Response.Status.BAD_REQUEST.getStatusCode()));
    }

    @ParameterizedTest
    @EnumSource(
        value = Response.Status.class,
        names = "OK",
        mode = EnumSource.Mode.EXCLUDE
    )
    void deveExecutarBateriaDeTestesFalseParaRequisicaoComSucessoOK(final Response.Status status) {
        Assertions.assertFalse(HttpStatusHelper.ok(status.getStatusCode()));
    }

    @ParameterizedTest
    @EnumSource(
        value = Response.Status.class,
        names = "BAD_REQUEST",
        mode = EnumSource.Mode.EXCLUDE
    )
    void deveExecutarBateriaDeTestesFalseParaRequisicaoComClientErrorBadRequest(final Response.Status status) {
        Assertions.assertFalse(HttpStatusHelper.clientError(status.getStatusCode()));
    }

    @ParameterizedTest
    @EnumSource(
        value = Response.Status.class,
        names = "INTERNAL_SERVER_ERROR",
        mode = EnumSource.Mode.EXCLUDE
    )
    void deveExecutarBateriaDeTestesFalseParaRequisicaoComInternalServerError(final Response.Status status) {
        Assertions.assertFalse(HttpStatusHelper.serverError(status.getStatusCode()));
    }
}

package dev.bstk.gatwayapi.domain.helper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@TestMethodOrder(MethodOrderer.MethodName.class)
class HttpStatusHelperTest {

    private static final int HTTP_STATUS_CODE_SUCESSO = 2;
    private static final int HTTP_STATUS_CODE_ERRO_CLEINTE = 4;
    private static final int HTTP_STATUS_CODE_ERRO_SERVIDOR = 5;

    @MethodSource("dadosMassaTesteStatusHttpSucesso")
    @DisplayName("Bateria de testes para requisição com sucesso")
    @ParameterizedTest(name = "Deve retonar [ TRUE ] para requisicao com status code: {0}")
    void deveRetonarTrueParaRequisicaoComSucessoOK(final Response.Status status) {
        Assertions.assertTrue(HttpStatusHelper.ok(status.getStatusCode()));
    }

    @MethodSource("dadosMassaTesteStatusHttpErroClienteServidor")
    @DisplayName("Bateria de testes para requisição com erro cliente/servidor")
    @ParameterizedTest(name = "Deve retonar [ TRUE ] para requisicao erro cliente/servidor e status code: {0}")
    void deveRetonarTrueParaRequisicaoComSucessoNOK(final Response.Status status) {
        Assertions.assertTrue(HttpStatusHelper.nok(status.getStatusCode()));
    }

    @MethodSource("dadosMassaTesteStatusHttpErroCliente")
    @DisplayName("Bateria de testes para requisição com erro do cliente")
    @ParameterizedTest(name = "Deve retonar [ TRUE ] para requisicao com status code: {0}")
    void deveRetonarTrueParaRequisicaoComClientErrorBadRequest(final Response.Status status) {
        Assertions.assertTrue(HttpStatusHelper.clientError(status.getStatusCode()));
    }

    @MethodSource("dadosMassaTesteStatusHttpErroServidor")
    @DisplayName("Bateria de testes para requisição com erro do servidor")
    @ParameterizedTest(name = "Deve retonar [ TRUE ] para requisicao com status code: {0}")
    void deveRetonarTrueParaRequisicaoComServerErrorInternalServerError(final Response.Status status) {
        Assertions.assertTrue(HttpStatusHelper.serverError(status.getStatusCode()));
    }

    private static Stream<Arguments> dadosMassaTesteStatusHttpSucesso() {
        return mapArgumentsStream(HTTP_STATUS_CODE_SUCESSO);
    }

    private static Stream<Arguments> dadosMassaTesteStatusHttpErroCliente() {
        return mapArgumentsStream(HTTP_STATUS_CODE_ERRO_CLEINTE);
    }

    private static Stream<Arguments> dadosMassaTesteStatusHttpErroServidor() {
        return mapArgumentsStream(HTTP_STATUS_CODE_ERRO_SERVIDOR);
    }

    private static Stream<Arguments> dadosMassaTesteStatusHttpErroClienteServidor() {
        final Stream.Builder<Arguments> builder = Stream.builder();

        mapHttpStatus(HTTP_STATUS_CODE_ERRO_CLEINTE).forEach(status -> builder.add(Arguments.of(status)));
        mapHttpStatus(HTTP_STATUS_CODE_ERRO_SERVIDOR).forEach(status -> builder.add(Arguments.of(status)));

        return builder.build();
    }

    private static Stream<Arguments> mapArgumentsStream(final int httpStatusCodeMultiplo) {
        final Stream.Builder<Arguments> builder = Stream.builder();

        mapHttpStatus(httpStatusCodeMultiplo)
            .forEach(status -> builder.add(Arguments.of(status)));

        return builder.build();
    }

    private static List<Response.Status> mapHttpStatus(final int httpStatusCodeMultiplo) {
        final Response.Status[] todosStatusCode = Response.Status.values();
        return Arrays.stream(todosStatusCode)
            .filter(status -> status.getStatusCode() / 100 == httpStatusCodeMultiplo)
            .collect(Collectors.toList());
    }
}

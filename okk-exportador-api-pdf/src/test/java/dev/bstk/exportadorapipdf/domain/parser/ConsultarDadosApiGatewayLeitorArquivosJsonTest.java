package dev.bstk.exportadorapipdf.domain.parser;

import dev.bstk.exportadorapipdf.gateway.request.ConsultaApiRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

class ConsultarDadosApiGatewayLeitorArquivosJsonTest {

    private final ConsultarDadosApiGatewayLeitorArquivosJson leitorArquivosJson = new ConsultarDadosApiGatewayLeitorArquivosJson();

    @Test
    void deveRetornarUmObjetoParseadoDeUmJsonValido() {
        mockResourceArquivosDadosJson("/repositorio-dados/consulta-dados-api-genius.json");

        final List<ConsultaApiRequest> requests = leitorArquivosJson.parse(ConsultaApiRequest.class);

        Assertions.assertNotNull(requests);
        Assertions.assertFalse(requests.isEmpty());
        Assertions.assertFalse(requests.get(0).getApis().isEmpty());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void deveLancarExcecaoComCaminhoJsonInvalidoNulo(final String arquivo) {
        mockResourceArquivosDadosJson(arquivo);

        final IllegalArgumentException exception = Assertions
                    .assertThrowsExactly(
                        IllegalArgumentException.class,
                        () -> leitorArquivosJson.parse(ConsultaApiRequest.class));

        Assertions.assertNotNull(exception);
        Assertions.assertEquals("Caminho arquivo json não pode ser nulo ou vazio!", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "repositorio-dados/consulta-dados-api-genius.json",
        "@repositorio-dados/consulta-dados-api-genius.json",
        "#repositorio-dados/consulta-dados-api-genius.json",
        "%repositorio-dados/consulta-dados-api-genius.json",
        "&repositorio-dados/consulta-dados-api-genius.json"
    })
    void deveLancarExcecaoComCaminhoJsonInvalidoNulo_A(final String arquivo) {
        mockResourceArquivosDadosJson(arquivo);

        final IllegalArgumentException exception = Assertions
            .assertThrowsExactly(
                IllegalArgumentException.class,
                () -> leitorArquivosJson.parse(ConsultaApiRequest.class));

        Assertions.assertNotNull(exception);
        Assertions.assertEquals("Caminho arquivo json deve iniciar com: '/'!", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "/repositorio-dados/consulta-dados-api-genius",
        "/repositorio-dados/consulta-dados-api-genius.txt",
        "/repositorio-dados/consulta-dados-api-genius.pdf",
        "/repositorio-dados/consulta-dados-api-genius.jar",
        "/repositorio-dados/consulta-dados-api-genius.yaml"
    })
    void deveLancarExcecaoComCaminhoJsonInvalidoNulo_B(final String arquivo) {
        mockResourceArquivosDadosJson(arquivo);

        final IllegalArgumentException exception = Assertions
            .assertThrowsExactly(
                IllegalArgumentException.class,
                () -> leitorArquivosJson.parse(ConsultaApiRequest.class));

        Assertions.assertNotNull(exception);
        Assertions.assertEquals("Caminho arquivo json ter a extensão .json!", exception.getMessage());
    }

    private void mockResourceArquivosDadosJson(final String arquivo) {
        leitorArquivosJson.arquivosDadosJson = new String[] { arquivo };
    }
}

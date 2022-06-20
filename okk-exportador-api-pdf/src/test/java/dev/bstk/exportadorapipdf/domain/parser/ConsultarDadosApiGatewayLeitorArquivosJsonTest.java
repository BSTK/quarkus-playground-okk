package dev.bstk.exportadorapipdf.domain.parser;

import dev.bstk.exportadorapipdf.gateway.request.ConsultaApiRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

class ConsultarDadosApiGatewayLeitorArquivosJsonTest {

    private final ConsultarDadosApiGatewayLeitorArquivosJson leitorArquivosJson = new ConsultarDadosApiGatewayLeitorArquivosJson();

    @Test
    @DisplayName("Deve retornar um objeto parseado de um json valido")
    void deveRetornarUmObjetoParseadoDeUmJsonValido() {
        mockResourceArquivosDadosJson("/repositorio-dados/consulta-dados-api-genius.json");

        final List<ConsultaApiRequest> requests = leitorArquivosJson.parse(ConsultaApiRequest.class);

        Assertions.assertNotNull(requests);
        Assertions.assertFalse(requests.isEmpty());
        Assertions.assertFalse(requests.get(0).getApis().isEmpty());
    }

    @NullAndEmptySource
    @ParameterizedTest(name = "Caso de teste: [ {0} ]")
    @DisplayName("Deve lancar exceçao com caminho json inválido nulo/vazio")
    void deveLancarExcecaoComCaminhoJsonInvalidoNulo(final String arquivo) {
        executarTesteLancarExcecao(arquivo, "Caminho arquivo json não pode ser nulo ou vazio!");
    }

    @ValueSource(strings = {
        "repositorio-dados/consulta-dados-api-genius.json",
        "@repositorio-dados/consulta-dados-api-genius.json",
        "#repositorio-dados/consulta-dados-api-genius.json",
        "%repositorio-dados/consulta-dados-api-genius.json",
        "&repositorio-dados/consulta-dados-api-genius.json"
    })
    @ParameterizedTest(name = "Caso de teste: [ {0} ]")
    @DisplayName("Deve lancar exceçao com caminho json inválido")
    void deveLancarExcecaoComCaminhoJsonInvalidoInicio(final String arquivo) {
        executarTesteLancarExcecao(arquivo, "Caminho arquivo json deve iniciar com: '/'!");
    }

    @ValueSource(strings = {
        "/repositorio-dados/consulta-dados-api-genius",
        "/repositorio-dados/consulta-dados-api-genius.txt",
        "/repositorio-dados/consulta-dados-api-genius.pdf",
        "/repositorio-dados/consulta-dados-api-genius.jar",
        "/repositorio-dados/consulta-dados-api-genius.yaml"
    })
    @ParameterizedTest(name = "Caso de teste: [ {0} ]")
    @DisplayName("Deve lancar exceçao com caminho json inválido")
    void deveLancarExcecaoComCaminhoJsonInvalidoExtensao(final String arquivo) {
        executarTesteLancarExcecao(arquivo, "Caminho arquivo json ter a extensão .json!");
    }

    void executarTesteLancarExcecao(final String arquivo,
                                    final String mensagemException) {
        mockResourceArquivosDadosJson(arquivo);

        final IllegalArgumentException exception = Assertions
            .assertThrowsExactly(
                IllegalArgumentException.class,
                () -> leitorArquivosJson.parse(ConsultaApiRequest.class));

        Assertions.assertNotNull(exception);
        Assertions.assertEquals(mensagemException, exception.getMessage());
    }

    private void mockResourceArquivosDadosJson(final String arquivo) {
        leitorArquivosJson.arquivosDadosJson = new String[] { arquivo };
    }
}

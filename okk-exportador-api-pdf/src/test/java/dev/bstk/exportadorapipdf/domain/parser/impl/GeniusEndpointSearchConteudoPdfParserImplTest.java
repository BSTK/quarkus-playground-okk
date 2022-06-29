package dev.bstk.exportadorapipdf.domain.parser.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import dev.bstk.exportadorapipdf.domain.model.genius.GeniusEndpointSearchConteudoPdf;
import dev.bstk.exportadorapipdf.gateway.response.ConsultaApiDadosItemResponse;
import dev.bstk.exportadorapipdf.gateway.response.ConsultaApiResponse;
import helper.TestHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.util.Collections;

class GeniusEndpointSearchConteudoPdfParserImplTest {

    private final GeniusEndpointSearchConteudoPdfParserImpl conteudoPdfParser = new GeniusEndpointSearchConteudoPdfParserImpl();


    @Test
    @DisplayName("Deve gerar conteudo para pdf")
    void deveGerarConteudoParaPdf() {
        final ConsultaApiDadosItemResponse itemResponse = new ConsultaApiDadosItemResponse();
        itemResponse.setNomeApiExterna("Genius Search");
        itemResponse.setResponse(TestHelper.parse("/genius-search-response.json", Object.class));

        final ConsultaApiResponse consultaApiResponse = new ConsultaApiResponse();
        consultaApiResponse.setDados(Collections.singletonList(itemResponse));
        consultaApiResponse.setDataHoraRequest(LocalDate.now().toString());

        final GeniusEndpointSearchConteudoPdf pdf = conteudoPdfParser.pdf(consultaApiResponse);

        Assertions.assertNotNull(pdf);
        Assertions.assertNotNull(pdf.getAno());
        Assertions.assertNotNull(pdf.getAlbum());
        Assertions.assertNotNull(pdf.getArtista());
        Assertions.assertNotNull(pdf.getFotoAlbum());
    }

    @Test
    @DisplayName("Deve lancar exceção de response inválida [ null ]")
    void deveLancarExcecaoDeResponseInvalidaNull() {
        final Exception exception = Assertions
            .assertThrows(
                IllegalStateException.class,
                () -> conteudoPdfParser.pdf(null)
            );

        Assertions.assertEquals("Response inválida! Reprocessar!", exception.getMessage());
    }

    @Test
    @Disabled
    @DisplayName("Deve lancar exceção de response inválida [ null ]")
    /// TODO: REFATORAR TESTE PARA CAIR NO CASO DE EXEÇÃO JsonProcessingException
    void deveLancarExcecaoDeJsonInvalido() {
        final ConsultaApiDadosItemResponse itemResponse = new ConsultaApiDadosItemResponse();
        itemResponse.setNomeApiExterna("Genius Search");
        itemResponse.setResponse(TestHelper.parse("/genius-search-response-com-json-invalido.json", Object.class));

        final ConsultaApiResponse consultaApiResponse = new ConsultaApiResponse();
        consultaApiResponse.setDados(Collections.singletonList(itemResponse));
        consultaApiResponse.setDataHoraRequest(LocalDate.now().toString());

        final Exception exception = Assertions
            .assertThrows(
                JsonProcessingException.class,
                () -> conteudoPdfParser.pdf(consultaApiResponse)
            );

        Assertions.assertEquals("Response inválida! Reprocessar!", exception.getMessage());
    }

    @NullAndEmptySource
    @ValueSource(strings = {
        "Genius Songs",
        "Genius Searc",
        "Genius Query"
    })
    @ParameterizedTest(name = "Caso de teste nomeApiExterna = [ {0} ]")
    @DisplayName("Deve retornar [ Null ] quando response não for do endpoint [ Search ]")
    void deveRetornarNullQuandoResponseNaoForDoEndpointSearch(final String nomeApiExterna) {
        executarAssertParaRetornoNull(nomeApiExterna, "/genius-search-response.json");
    }

    @Test
    @DisplayName("Deve retornar [ Null ] quando response vier com status diferente de 200")
    void deveRetornarNullQuandoResponseVierComStatusDiferenteDe200() {
        executarAssertParaRetornoNull(
            "Genius Search",
            "/genius-search-response-com-status-code-400-erro.json");
    }

    @Test
    @DisplayName("Deve retornar [ Null ] quando response sem dados de retorno")
    void deveRetornarNullQuandoResponseSemDadosDeRetorno() {
        executarAssertParaRetornoNull(
            "Genius Search",
            "/genius-search-response-sem-dados-de-resultado.json");
    }

    void executarAssertParaRetornoNull(final String nomeApiExterna,
                                       final String arquivoResponse) {
        final ConsultaApiDadosItemResponse itemResponse = new ConsultaApiDadosItemResponse();
        itemResponse.setNomeApiExterna(nomeApiExterna);
        itemResponse.setResponse(TestHelper.parse(arquivoResponse, Object.class));

        final ConsultaApiResponse consultaApiResponse = new ConsultaApiResponse();
        consultaApiResponse.setDados(Collections.singletonList(itemResponse));
        consultaApiResponse.setDataHoraRequest(LocalDate.now().toString());

        final GeniusEndpointSearchConteudoPdf pdf = conteudoPdfParser.pdf(consultaApiResponse);

        Assertions.assertNull(pdf);
    }
}

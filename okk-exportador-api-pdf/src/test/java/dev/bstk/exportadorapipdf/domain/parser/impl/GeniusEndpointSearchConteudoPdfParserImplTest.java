package dev.bstk.exportadorapipdf.domain.parser.impl;

import dev.bstk.exportadorapipdf.domain.parser.ConteudoPdfParser;
import dev.bstk.exportadorapipdf.domain.parser.model.GeniusEndpointSearchConteudoPdf;
import dev.bstk.exportadorapipdf.gateway.response.ConsultaApiDadosItemResponse;
import dev.bstk.exportadorapipdf.gateway.response.ConsultaApiResponse;
import helper.TestHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;

class GeniusEndpointSearchConteudoPdfParserImplTest {

    private final ConteudoPdfParser<GeniusEndpointSearchConteudoPdf> conteudoPdfParser = new GeniusEndpointSearchConteudoPdfParserImpl();


    @Test
    void deveGerarConteudoParaPdf() {
        final ConsultaApiDadosItemResponse itemResponse = new ConsultaApiDadosItemResponse();
        itemResponse.setNomeApiExterna("Genius Search");
        itemResponse.setResponse(TestHelper.parse("/genius-search-response.json", Object.class));

        final ConsultaApiResponse consultaApiResponse = new ConsultaApiResponse();
        consultaApiResponse.setDados(Collections.singletonList(itemResponse));
        consultaApiResponse.setDataHoraRequest(LocalDate.now().toString());

        final GeniusEndpointSearchConteudoPdf pdf = conteudoPdfParser.pdf(consultaApiResponse);

        Assertions.assertNotNull(pdf);
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
}

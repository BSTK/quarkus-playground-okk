package dev.bstk.exportadorapipdf.domain.parser.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import dev.bstk.exportadorapipdf.domain.parser.ConteudoPdfParser;
import dev.bstk.exportadorapipdf.domain.parser.model.ApiResponse;
import dev.bstk.exportadorapipdf.domain.parser.model.GeniusEndpointSearchConteudoPdf;
import dev.bstk.exportadorapipdf.gateway.response.ConsultaApiDadosItemResponse;
import dev.bstk.exportadorapipdf.gateway.response.ConsultaApiResponse;
import dev.bstk.exportadorapipdf.helper.JSONHelper;

import javax.enterprise.context.ApplicationScoped;
import java.io.File;
import java.util.Objects;
import java.util.Optional;

@ApplicationScoped
public class GeniusEndpointSearchConteudoPdfParserImpl implements ConteudoPdfParser<GeniusEndpointSearchConteudoPdf> {

    private static final String GENIUS_ENDPOINT_SEARCH = "Search";


    @Override
    public GeniusEndpointSearchConteudoPdf pdf(ConsultaApiResponse response) {
        if (Objects.isNull(response)) {
            throw new IllegalStateException("Response inválida! Reprocessar!");
        }

        final Optional<ConsultaApiDadosItemResponse> responseGeniusEndpointSearc = response
            .getDados()
            .stream()
            .filter(dado -> dado.getNomeApiExterna().toLowerCase().contains(GENIUS_ENDPOINT_SEARCH.toLowerCase()))
            .findFirst();

        if (responseGeniusEndpointSearc.isEmpty()) {
            return null;
        }

        try {
            ConsultaApiDadosItemResponse consultaApiDadosItemResponse = responseGeniusEndpointSearc.get();
            final String responseJson = JSONHelper.mapper().writeValueAsString(consultaApiDadosItemResponse.getResponse());
            final ApiResponse apiResponse = JSONHelper.mapper().readValue(responseJson, ApiResponse.class);

            if (apiResponse.getMeta().getStatus() == 200) {
                System.out.println("** SUCESSO **");
                System.out.println("** SUCESSO **");
                System.out.println("-------------");
            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        final GeniusEndpointSearchConteudoPdf conteudoPdf = new GeniusEndpointSearchConteudoPdf();
        conteudoPdf.setAno("2020");
        conteudoPdf.setAlgum("NOME_ALGUM");
        conteudoPdf.setMusica("NOME_DA_MUSICA");
        conteudoPdf.setArtista("NOME_DO_ARTISTA");
        conteudoPdf.setFotoAlbum(new File(""));

        return conteudoPdf;
    }
}

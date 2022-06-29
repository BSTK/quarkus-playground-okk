package dev.bstk.exportadorapipdf.domain.parser.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.module.SimpleModule;
import dev.bstk.exportadorapipdf.domain.parser.ConteudoPdfParser;
import dev.bstk.exportadorapipdf.domain.model.genius.GeniusEndpointSearchConteudoPdf;
import dev.bstk.exportadorapipdf.domain.model.genius.GeniusSearchArtistResponse;
import dev.bstk.exportadorapipdf.domain.model.genius.GeniusSearchArtistResponseDeserializer;
import dev.bstk.exportadorapipdf.gateway.response.ConsultaApiDadosItemResponse;
import dev.bstk.exportadorapipdf.gateway.response.ConsultaApiResponse;
import dev.bstk.exportadorapipdf.helper.GeradorNumeroAleatorio;
import dev.bstk.exportadorapipdf.helper.JSONHelper;

import javax.enterprise.context.ApplicationScoped;
import java.io.File;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@ApplicationScoped
public class GeniusEndpointSearchConteudoPdfParserImpl implements ConteudoPdfParser<GeniusEndpointSearchConteudoPdf> {

    private static final String API_GENIUS = "Genius";
    private static final String GENIUS_ENDPOINT_SEARCH = "Search";


    @Override
    public GeniusEndpointSearchConteudoPdf pdf(ConsultaApiResponse response) {
        if (Objects.isNull(response)) {
            /// TODO: LANÇAR EXCEÇÃO DE Response inválida
            ///  TODO: E NÃO TENTAR REPROCESSAR!
            throw new IllegalStateException("Response inválida! Reprocessar!");
        }

        final Optional<ConsultaApiDadosItemResponse> responseGeniusEndpointSearc = response
            .getDados()
            .stream()
            .filter(this::filtrarApiGeniusSearch)
            .findFirst();

        if (responseGeniusEndpointSearc.isEmpty()) {
            /// TODO: LANÇAR EXCEÇÃO DE Response inválida
            ///  TODO: E NÃO TENTAR REPROCESSAR!
            return null;
        }

        try {
            ConsultaApiDadosItemResponse consultaApiDadosItemResponse = responseGeniusEndpointSearc.get();
            final String responseJson = JSONHelper.mapper().writeValueAsString(consultaApiDadosItemResponse.getResponse());

            /// TODO: REFATORAR REGISTRO DE MÓDULOS
            final GeniusSearchArtistResponse geniusSearchArtistResponse = JSONHelper
                .mapper()
                .registerModule(
                    new SimpleModule()
                        .addDeserializer(GeniusSearchArtistResponse.class, new GeniusSearchArtistResponseDeserializer()))
                .readValue(responseJson, GeniusSearchArtistResponse.class);
            final List<GeniusSearchArtistResponse.Dado> dados = geniusSearchArtistResponse.getDados();

            /// TODO: !dado.isEmpty() -> USAR BIBLIOTECA OKK-UTILS
            if (geniusSearchArtistResponse.getMeta().getStatus() != HttpURLConnection.HTTP_OK || dados.isEmpty()) {
                return null;
            }

            final GeniusSearchArtistResponse.Dado dado = dados.get(GeradorNumeroAleatorio.get(dados.size()));
            final GeniusEndpointSearchConteudoPdf conteudoPdf = new GeniusEndpointSearchConteudoPdf();
            conteudoPdf.setAno(dado.getAno());
            conteudoPdf.setAlbum(dado.getAlbum());
            conteudoPdf.setMusica(dado.getMusica());
            conteudoPdf.setArtista(dado.getArtista());
            conteudoPdf.setFotoAlbum(new File(dado.getImage()));

            return conteudoPdf;
        } catch (JsonProcessingException ex) {
            /// TODO: LANÇAR EXCEÇÃO DE NÃO FOI POSSIVÉL PARSEAR O JSON DE RESPOSTA
            //  TODO: E NÃO TENTAR REPROCESSAR!
            throw new IllegalStateException("Response inválida! Reprocessar!", ex);
        }
    }

    private boolean filtrarApiGeniusSearch(final ConsultaApiDadosItemResponse dado) {
        /// TODO: !resultado.isEmpty() -> USAR BIBLIOTECA OKK-UTILS
        return Objects.nonNull(dado.getNomeApiExterna())
            && !dado.getNomeApiExterna().isEmpty()
            && !dado.getNomeApiExterna().isBlank()
            && dado.getNomeApiExterna().toLowerCase().contains(API_GENIUS.toLowerCase())
            && dado.getNomeApiExterna().toLowerCase().contains(GENIUS_ENDPOINT_SEARCH.toLowerCase());
    }
}

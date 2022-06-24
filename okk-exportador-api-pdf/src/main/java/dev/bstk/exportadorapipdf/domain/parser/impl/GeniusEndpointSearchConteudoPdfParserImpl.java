package dev.bstk.exportadorapipdf.domain.parser.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import dev.bstk.exportadorapipdf.domain.parser.ConteudoPdfParser;
import dev.bstk.exportadorapipdf.domain.parser.model.GeniusEndpointSearchConteudoPdf;
import dev.bstk.exportadorapipdf.domain.parser.model.genius.GeniusSearchArtistResponse;
import dev.bstk.exportadorapipdf.gateway.response.ConsultaApiDadosItemResponse;
import dev.bstk.exportadorapipdf.gateway.response.ConsultaApiResponse;
import dev.bstk.exportadorapipdf.helper.GeradorNumeroAleatorio;
import dev.bstk.exportadorapipdf.helper.JSONHelper;

import javax.enterprise.context.ApplicationScoped;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.List;
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
            final GeniusSearchArtistResponse geniusSearchArtistResponse = JSONHelper.mapper().readValue(responseJson, GeniusSearchArtistResponse.class);
            final List<GeniusSearchArtistResponse.Resultado> resultado = geniusSearchArtistResponse.getResultado();

            /// TODO: !resultado.isEmpty() -> USAR BIBLIOTECA OKK-UTILS
            if (geniusSearchArtistResponse.getMeta().getStatus() != HttpURLConnection.HTTP_OK && !resultado.isEmpty()) {
                return null;
            }

            final GeniusSearchArtistResponse.Resultado hit = resultado.get(GeradorNumeroAleatorio.get(resultado.size()));
            final GeniusEndpointSearchConteudoPdf conteudoPdf = new GeniusEndpointSearchConteudoPdf();
            conteudoPdf.setAno(hit.getAno());
            conteudoPdf.setAlgum(hit.getAlbum());
            conteudoPdf.setMusica(hit.getMusica());
            conteudoPdf.setArtista(hit.getArtista());
            conteudoPdf.setFotoAlbum(new File(URI.create(hit.getImage())));

            return conteudoPdf;
        } catch (JsonProcessingException ex) {
            throw new IllegalStateException("Response inválida! Reprocessar!", ex);
        }
    }
}

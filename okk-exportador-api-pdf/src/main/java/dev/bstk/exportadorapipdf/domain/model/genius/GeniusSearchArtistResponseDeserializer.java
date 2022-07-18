package dev.bstk.exportadorapipdf.domain.model.genius;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class GeniusSearchArtistResponseDeserializer extends StdDeserializer<GeniusSearchArtistResponse> {

    private static final String GENIUS_SEARCH_ARTIST_PATH_RESULT = "result";

    public GeniusSearchArtistResponseDeserializer() {
        this(null);
    }

    protected GeniusSearchArtistResponseDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public GeniusSearchArtistResponse deserialize(final JsonParser jsonParser,
                                                  final DeserializationContext context) throws IOException {
        final JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);

        final GeniusSearchArtistResponse.Meta meta = new GeniusSearchArtistResponse.Meta();
        meta.setStatus(jsonNode.get("meta").get("status").intValue());

        final List<GeniusSearchArtistResponse.Dado> dados = new ArrayList<>();
        final Iterator<JsonNode> elements = jsonNode.get("response").get("hits").elements();

        if (Objects.nonNull(elements)) {
            while (elements.hasNext()) {
                final JsonNode json = elements.next();
                final GeniusSearchArtistResponse.Dado dadoArtista = new GeniusSearchArtistResponse.Dado();
                dadoArtista.setMusica(json.get(GENIUS_SEARCH_ARTIST_PATH_RESULT).get("title").textValue());
                dadoArtista.setAlbum(json.get(GENIUS_SEARCH_ARTIST_PATH_RESULT).get("artist_names").textValue());
                dadoArtista.setImage(json.get(GENIUS_SEARCH_ARTIST_PATH_RESULT).get("header_image_thumbnail_url").textValue());
                dadoArtista.setArtista(json.get(GENIUS_SEARCH_ARTIST_PATH_RESULT).get("primary_artist").get("name").textValue());

                if (json.get(GENIUS_SEARCH_ARTIST_PATH_RESULT).has("release_date_components")) {
                    final int ano = json.get(GENIUS_SEARCH_ARTIST_PATH_RESULT).get("release_date_components").get("year").intValue();
                    dadoArtista.setAno(String.valueOf(ano));
                }

                dados.add(dadoArtista);
            }
        }

        final GeniusSearchArtistResponse response = new GeniusSearchArtistResponse();
        response.setMeta(meta);
        response.setDados(dados);

        return response;
    }
}

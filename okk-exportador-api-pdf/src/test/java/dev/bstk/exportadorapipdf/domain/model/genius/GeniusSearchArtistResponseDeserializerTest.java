package dev.bstk.exportadorapipdf.domain.model.genius;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

class GeniusSearchArtistResponseDeserializerTest {

    private final ObjectMapper mapper = new ObjectMapper();
    private final GeniusSearchArtistResponseDeserializer deserializer = new GeniusSearchArtistResponseDeserializer();


    @Test
    @DisplayName("Deve desserializar um json válido")
    void deveDesserializarUmJsonValido() throws IOException {
        final GeniusSearchArtistResponse response = deserializer.deserialize(
            mapper.createParser(new File("src/test/resources/genius-search-response.json")),
            mapper.getDeserializationContext());

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getMeta());
        Assertions.assertEquals(200, response.getMeta().getStatus());

        Assertions.assertNotNull(response.getDados());
        Assertions.assertFalse(response.getDados().isEmpty());

        final GeniusSearchArtistResponse.Dado dado = response.getDados().get(0);
        Assertions.assertNotNull(dado.getAno());
        Assertions.assertNotNull(dado.getAlbum());
        Assertions.assertNotNull(dado.getImage());
        Assertions.assertNotNull(dado.getArtista());
    }

    @Test
    @DisplayName("Deve desserializar um json válido sem dados de data")
    void deveDesserializarUmJsonValidoSemDadosDeData() throws IOException {
        final GeniusSearchArtistResponse response = deserializer.deserialize(
            mapper.createParser(new File("src/test/resources/genius-search-response-sem-dados-de-data.json")),
            mapper.getDeserializationContext());

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getMeta());
        Assertions.assertEquals(200, response.getMeta().getStatus());

        Assertions.assertNotNull(response.getDados());
        Assertions.assertFalse(response.getDados().isEmpty());

        final GeniusSearchArtistResponse.Dado dado = response.getDados().get(0);
        Assertions.assertNull(dado.getAno());
        Assertions.assertNotNull(dado.getAlbum());
        Assertions.assertNotNull(dado.getImage());
        Assertions.assertNotNull(dado.getArtista());
    }

    @Test
    @DisplayName("Deve desserializar um json valido sem dados de retorno")
    void deveDesserializarUmJsonValidoSemDadosDeRetorno() throws IOException {
        final GeniusSearchArtistResponse response = deserializer.deserialize(
            mapper.createParser(new File("src/test/resources/genius-search-response-sem-dados-de-resultado.json")),
            mapper.getDeserializationContext());

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getMeta());
        Assertions.assertEquals(400, response.getMeta().getStatus());

        Assertions.assertNotNull(response.getDados());
        Assertions.assertTrue(response.getDados().isEmpty());
    }
}

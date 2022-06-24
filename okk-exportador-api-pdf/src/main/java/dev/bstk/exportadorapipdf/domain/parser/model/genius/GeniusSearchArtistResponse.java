package dev.bstk.exportadorapipdf.domain.parser.model.genius;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.json.bind.annotation.JsonbProperty;
import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GeniusSearchArtistResponse implements Serializable {

    @JsonbProperty("meta")
    private Meta meta;

    @JsonbProperty("hits")
    private List<Resultado> resultado;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<Resultado> getResultado() {
        return resultado;
    }

    public void setResultado(List<Resultado> resultado) {
        this.resultado = resultado;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Meta implements Serializable {

        @JsonbProperty("status")
        private int status;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Resultado implements Serializable {

        @JsonbProperty("result.primary_artist.name")
        private String artista;

        @JsonbProperty("result.artist_names")
        private String album;

        @JsonbProperty("result.title")
        private String musica;

        @JsonbProperty("result.header_image_thumbnail_url")
        private String image;

        @JsonbProperty("result.release_date_components.year")
        private String ano;

        public String getArtista() {
            return artista;
        }

        public void setArtista(String artista) {
            this.artista = artista;
        }

        public String getMusica() {
            return musica;
        }

        public void setMusica(String musica) {
            this.musica = musica;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getAno() {
            return ano;
        }

        public void setAno(String ano) {
            this.ano = ano;
        }

        public String getAlbum() {
            return album;
        }

        public void setAlbum(String album) {
            this.album = album;
        }
    }
}

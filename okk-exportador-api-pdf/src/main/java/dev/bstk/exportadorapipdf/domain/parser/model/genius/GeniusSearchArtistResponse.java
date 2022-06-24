package dev.bstk.exportadorapipdf.domain.parser.model.genius;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.json.bind.annotation.JsonbProperty;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GeniusSearchArtistResponse implements Serializable {

    @JsonbProperty("meta")
    private Meta meta;

    @JsonbProperty("hits")
    private List<Hit> hits;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<Hit> getHits() {
        return hits;
    }

    public void setHits(List<Hit> hits) {
        this.hits = hits;
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
    public static class Hit implements Serializable {

        @JsonbProperty("result.artist_names")
        private String artista;

        @JsonbProperty("result.title")
        private String musica;

        @JsonbProperty("result.header_image_thumbnail_url")
        private String image;

        @JsonbProperty("result.release_date_components.day")
        private int dia;

        @JsonbProperty("result.release_date_components.month")
        private int mes;

        @JsonbProperty("result.release_date_components.year")
        private int ano;

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

        public LocalDate getData() {
            return LocalDate.of(ano, mes, dia);
        }

        public void setDia(int dia) {
            this.dia = dia;
        }

        public void setMes(int mes) {
            this.mes = mes;
        }

        public void setAno(int ano) {
            this.ano = ano;
        }

    }
}

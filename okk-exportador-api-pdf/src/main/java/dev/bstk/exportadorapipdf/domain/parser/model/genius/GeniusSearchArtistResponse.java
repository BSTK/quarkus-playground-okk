package dev.bstk.exportadorapipdf.domain.parser.model.genius;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.json.bind.annotation.JsonbProperty;
import java.io.Serializable;
import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GeniusSearchArtistResponse implements Serializable {

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

    @Override
    public String toString() {
        return "GeniusSearchArtistResponse { " +
            "artista='" + artista + '\'' +
            ", musica='" + musica + '\'' +
            ", image='" + image + '\'' +
            ", data='" + getData() + '\'' +
            '}';
    }
}

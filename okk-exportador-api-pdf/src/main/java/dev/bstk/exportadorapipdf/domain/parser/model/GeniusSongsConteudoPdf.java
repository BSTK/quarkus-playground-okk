package dev.bstk.exportadorapipdf.domain.parser.model;

import java.io.File;
import java.io.Serializable;

public class GeniusSongsConteudoPdf implements Serializable {

    private File fotoAlbum;
    private String musica;
    private String artista;
    private String algum;
    private String ano;

    public File getFotoAlbum() {
        return fotoAlbum;
    }

    public void setFotoAlbum(File fotoAlbum) {
        this.fotoAlbum = fotoAlbum;
    }

    public String getMusica() {
        return musica;
    }

    public void setMusica(String musica) {
        this.musica = musica;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getAlgum() {
        return algum;
    }

    public void setAlgum(String algum) {
        this.algum = algum;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    @Override
    public String toString() {
        return "GeniusSongsConteudoPdf {"
            + "fotoAlbum=" + fotoAlbum
            + ", musica='" + musica + '\''
            + ", artista='" + artista + '\''
            + ", algum='" + algum + '\''
            + ", ano='" + ano + '\''
            + '}';
    }
}

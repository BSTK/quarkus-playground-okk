package dev.bstk.exportadorapipdf.domain.model.genius;

import java.io.Serializable;

public class GeniusEndpointSearchConteudoPdf implements Serializable {

    private String fotoAlbum;
    private String musica;
    private String artista;
    private String album;
    private String ano;

    public String getFotoAlbum() {
        return fotoAlbum;
    }

    public void setFotoAlbum(String fotoAlbum) {
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

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
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
            + ", album='" + album + '\''
            + ", ano='" + ano + '\''
            + '}';
    }
}

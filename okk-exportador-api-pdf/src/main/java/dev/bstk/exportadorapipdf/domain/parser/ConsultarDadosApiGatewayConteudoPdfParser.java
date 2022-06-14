package dev.bstk.exportadorapipdf.domain.parser;

import dev.bstk.exportadorapipdf.gateway.response.ConsultaApiResponse;
import dev.bstk.exportadorapipdf.domain.parser.model.GeniusSongsConteudoPdf;

import javax.enterprise.context.ApplicationScoped;
import java.io.File;
import java.util.Objects;

@ApplicationScoped
public class ConsultarDadosApiGatewayConteudoPdfParser {


    /// TODO: LANCAR EXCESSÃO CASO ALGO ESTEJA INCORRETO
    public GeniusSongsConteudoPdf dadosPdf(final ConsultaApiResponse response) {
        if (Objects.isNull(response)) {
            throw new IllegalStateException("Response inválida! Reprocessar!");
        }

        final GeniusSongsConteudoPdf conteudoPdf = new GeniusSongsConteudoPdf();
        conteudoPdf.setAno("2020");
        conteudoPdf.setAlgum("NOME_ALGUM");
        conteudoPdf.setMusica("NOME_DA_MUSICA");
        conteudoPdf.setArtista("NOME_DO_ARTISTA");
        conteudoPdf.setFotoAlbum(new File(""));

        return conteudoPdf;
    }
}

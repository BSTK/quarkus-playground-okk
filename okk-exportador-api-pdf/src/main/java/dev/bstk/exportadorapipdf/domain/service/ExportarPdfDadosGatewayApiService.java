package dev.bstk.exportadorapipdf.domain.service;

import dev.bstk.exportadorapipdf.domain.model.ConteudoPdf;
import dev.bstk.exportadorapipdf.domain.model.genius.GeniusEndpointSearchConteudoPdf;
import dev.bstk.exportadorapipdf.domain.model.genius.GeniusEndpointSearchConteudoPdfRepository;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class ExportarPdfDadosGatewayApiService {

    private static final Logger LOG = LoggerFactory.getLogger(ExportarPdfDadosGatewayApiService.class);

    @ConfigProperty(name = "exportadorpdf.arquivos.tempplatehtml")
    protected String geniusTemplateHtml;

    /// TODO: REFATORAR PARA FICAR GENÉRICO
    @Inject
    protected GeniusEndpointSearchConteudoPdfRepository conteudoPdfRepository;


    @Transactional
    public void exportar() {
        final List<ConteudoPdf> conteudoPdfs = conteudoPdfRepository.pdfsParaExportar();
        for (ConteudoPdf conteudo : conteudoPdfs) {
            final GeniusEndpointSearchConteudoPdf dados = (GeniusEndpointSearchConteudoPdf) conteudo.getDados();
            final URL templateHtmlResource = ExportarPdfDadosGatewayApiService.class.getResource(geniusTemplateHtml);

            if (Objects.isNull(templateHtmlResource)) {
                throw new IllegalArgumentException("Não foi possivél ler arquivo de template html!");
            }

            try (final InputStream inputStream = templateHtmlResource.openStream()) {
                if (Objects.isNull(inputStream)) {
                    throw new IllegalArgumentException("Não foi possivél ler arquivo de template html!");
                }

                final String templateHtml = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
                LOG.info("HTML INICIO -> {}", templateHtml);

                final String templateHtmlParseado = templateHtml
                    .replace("$FOTO_ALBUM", dados.getFotoAlbum())
                    .replace("$MUSICA", dados.getMusica())
                    .replace("$ANO", dados.getAno())
                    .replace("$ARTISTA", dados.getArtista())
                    .replace("$ALBUM", dados.getAlbum());

                LOG.info("HTML FINAL -> {}", templateHtmlParseado);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}

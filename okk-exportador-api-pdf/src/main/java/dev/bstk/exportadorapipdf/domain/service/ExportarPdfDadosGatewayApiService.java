package dev.bstk.exportadorapipdf.domain.service;

import com.itextpdf.html2pdf.HtmlConverter;
import dev.bstk.exportadorapipdf.domain.model.ConteudoPdf;
import dev.bstk.exportadorapipdf.domain.model.genius.GeniusEndpointSearchConteudoPdf;
import dev.bstk.exportadorapipdf.domain.model.genius.GeniusEndpointSearchConteudoPdfRepository;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class ExportarPdfDadosGatewayApiService {

    private static final Logger LOG = LoggerFactory.getLogger(ExportarPdfDadosGatewayApiService.class);


    @ConfigProperty(name = "exportadorpdf.arquivos.tempplatehtml")
    protected String geniusTemplateHtml;

    @ConfigProperty(name = "exportadorpdf.arquivos.caminhopastasalvarpdf")
    protected String caminhoPastaSalvarPdf;

    /// TODO: REFATORAR PARA FICAR GENÉRICO
    @Inject
    protected GeniusEndpointSearchConteudoPdfRepository conteudoPdfRepository;


    @Transactional
    public void exportar() {
        final List<ConteudoPdf> conteudoPdfs = conteudoPdfRepository.pdfsParaExportar();
        if (conteudoPdfs.isEmpty()) {
            LOG.warn("** NÃO HÁ PDF PARA EXPORTAR! **");
            return;
        }

        for (ConteudoPdf conteudo : conteudoPdfs) {

            /// TODO: REFATORAR PARA FICAR GENÉRICO
            final GeniusEndpointSearchConteudoPdf dados = conteudo.getDados();
            final URL templateHtmlResource = ExportarPdfDadosGatewayApiService.class.getResource(geniusTemplateHtml);

            if (Objects.isNull(templateHtmlResource)) {
                throw new IllegalArgumentException("Não foi possivél ler arquivo de template html!");
            }

            try (final InputStream inputStream = templateHtmlResource.openStream()) {
                final String templateHtmlParseado = templateHtmlParseado(inputStream, dados);
                final String caminhoCompletoArquivoPdf = caminhoCompletoArquivoPdf();

                HtmlConverter.convertToPdf(templateHtmlParseado, new FileOutputStream(caminhoCompletoArquivoPdf));
            } catch (Exception ex) {
                /// TODO: TRATAR EXCEPTION
                ex.printStackTrace();
            }
        }
    }

    private String templateHtmlParseado(final InputStream inputStream,
                                        final GeniusEndpointSearchConteudoPdf dados) throws IOException {
        final String templateHtml = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        return templateHtml
            .replace("$ANO", dados.getAno())
            .replace("$ALBUM", dados.getAlbum())
            .replace("$MUSICA", dados.getMusica())
            .replace("$ARTISTA", dados.getArtista())
            .replace("$FOTO_ALBUM", dados.getFotoAlbum());
    }

    private String caminhoCompletoArquivoPdf() {
        return String
            .format(
                "%s-%s.pdf",
                caminhoPastaSalvarPdf,
                DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(LocalDateTime.now()));
    }
}

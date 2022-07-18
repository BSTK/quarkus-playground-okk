package dev.bstk.exportadorapipdf.domain.service;

import dev.bstk.exportadorapipdf.domain.model.ConteudoPdf;
import dev.bstk.exportadorapipdf.domain.model.genius.GeniusEndpointSearchConteudoPdfRepository;
import helper.TestHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExportarPdfDadosGatewayApiServiceTest {

    @InjectMocks
    private ExportarPdfDadosGatewayApiService exportarPdfDadosGatewayApiService;

    @Mock
    private GeniusEndpointSearchConteudoPdfRepository conteudoPdfRepository;


    @Test
    @Disabled
    @DisplayName("Deve exportar dados em formato PDF")
    /// TODO: REFATORAR - VALIDAR SE OS PDF'S FORAM CRIADOS E DEPOIS EXCLUI-LOS
    void deveExportarDadosEmFormatoPdf() {
        final ConteudoPdf[] conteudoMock = TestHelper.parse("/model/conteudo-pdf.json", ConteudoPdf[].class);
        when(conteudoPdfRepository.pdfsParaExportar()).thenReturn(Arrays.asList(conteudoMock));

        exportarPdfDadosGatewayApiService.geniusTemplateHtml = "/template-html/template-pdf-genius-artista.html";
        exportarPdfDadosGatewayApiService.caminhoPastaSalvarPdf = "src/test/resources/pdf/arquivo-exportado";
        exportarPdfDadosGatewayApiService.exportar();
    }

    @Test
    @DisplayName("Deve parar execução quando não houver nenhum pdf para ser exportado")
    void devePararExecucaoQuandoNaoHouverNenhumPdfParaSerExportado() {
        when(conteudoPdfRepository.pdfsParaExportar()).thenReturn(Collections.emptyList());

        Assertions.assertDoesNotThrow(() -> exportarPdfDadosGatewayApiService.exportar());
    }

    @Test
    @DisplayName("Deve lancar exceção não foi possivel carregar template html")
    void deveLancarExcecaoNaoFoiPossivelCarregarTemplateHtml() {
        final ConteudoPdf[] conteudoMock = TestHelper.parse("/model/conteudo-pdf.json", ConteudoPdf[].class);
        when(conteudoPdfRepository.pdfsParaExportar()).thenReturn(Arrays.asList(conteudoMock));

        exportarPdfDadosGatewayApiService.geniusTemplateHtml = "/url-invalida";

        final IllegalArgumentException exception = Assertions
            .assertThrowsExactly(
                IllegalArgumentException.class,
                () -> exportarPdfDadosGatewayApiService.exportar());

        Assertions.assertNotNull(exception);
        Assertions.assertEquals("Não foi possivél ler arquivo de template html!", exception.getMessage());
    }
}

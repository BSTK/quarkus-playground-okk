package dev.bstk.exportadorapipdf.domain.service;

import dev.bstk.exportadorapipdf.domain.model.genius.GeniusEndpointSearchConteudoPdfRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExportarPdfDadosGatewayApiServiceTest {

    @InjectMocks
    private ExportarPdfDadosGatewayApiService exportarPdfDadosGatewayApiService;

    @Mock
    private GeniusEndpointSearchConteudoPdfRepository conteudoPdfRepository;


    @Test
    @DisplayName("Deve parar execução quando não houver nenhum pdf para ser exportado")
    void devePararExecucaoQuandoNaoHouverNenhumPdfParaSerExportado() {
        when(conteudoPdfRepository.pdfsParaExportar()).thenReturn(Collections.emptyList());
    }
}

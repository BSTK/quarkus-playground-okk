package dev.bstk.exportadorapipdf.domain.service;

import dev.bstk.exportadorapipdf.domain.model.genius.GeniusEndpointSearchConteudoPdf;
import dev.bstk.exportadorapipdf.domain.model.genius.GeniusEndpointSearchConteudoPdfRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ExportarPdfDadosGatewayApiService {

    /// TODO: REFATORAR PARA FICAR GENÉRICO
    @Inject
    protected GeniusEndpointSearchConteudoPdfRepository geniusEndpointSearchConteudoPdfRepository;

    public void exportar() {
        geniusEndpointSearchConteudoPdfRepository
            .pdfsParaExportar()
            .forEach(conteudoPdf -> {
                final GeniusEndpointSearchConteudoPdf dados = conteudoPdf.getDados();
                /// GERAR PDF EM UM CAMINHO ESPECIFICO COM DADOS: dados

                geniusEndpointSearchConteudoPdfRepository.delete(conteudoPdf);
            });
    }
}

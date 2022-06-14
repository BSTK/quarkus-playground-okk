package dev.bstk.exportadorapipdf.domain.service;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ExportarPdfDadosGatewayApiService {

    public void exportar() {
        System.out.println("Exportando dados PDF");
        System.out.println("\n");
    }
}

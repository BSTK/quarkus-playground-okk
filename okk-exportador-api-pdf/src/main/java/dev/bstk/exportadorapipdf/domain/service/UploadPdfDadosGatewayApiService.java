package dev.bstk.exportadorapipdf.domain.service;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UploadPdfDadosGatewayApiService {

    public void upload() {
        System.out.println("Fazendo upload do PDF gerado");
    }
}

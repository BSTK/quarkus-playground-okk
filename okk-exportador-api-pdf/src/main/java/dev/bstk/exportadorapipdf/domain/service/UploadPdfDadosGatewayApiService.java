package dev.bstk.exportadorapipdf.domain.service;

import org.eclipse.microprofile.context.ManagedExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class UploadPdfDadosGatewayApiService {

    private static final Logger LOG = LoggerFactory.getLogger(UploadPdfDadosGatewayApiService.class);

    @Inject
    protected ManagedExecutor managedExecutor;


    public void upload() {
        managedExecutor.submit(() -> {
            LOG.info("Fazendo upload do PDF gerado");
            LOG.info("\n");
        });
    }
}

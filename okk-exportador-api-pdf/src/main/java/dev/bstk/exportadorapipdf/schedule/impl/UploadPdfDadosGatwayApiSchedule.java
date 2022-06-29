package dev.bstk.exportadorapipdf.schedule.impl;

import dev.bstk.exportadorapipdf.domain.service.UploadPdfDadosGatewayApiService;
import dev.bstk.exportadorapipdf.schedule.OkkSchedule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class UploadPdfDadosGatwayApiSchedule implements OkkSchedule {

    private static final Logger LOG = LoggerFactory.getLogger(UploadPdfDadosGatwayApiSchedule.class);

    @Inject
    protected UploadPdfDadosGatewayApiService uploadPdfDadosGatewayApiService;

    @Override
    // @Scheduled(every = "10s")
    public void executar() {
        LOG.info("Executando UploadPdfDadosGatwayApiSchedule ...");
        uploadPdfDadosGatewayApiService.upload();
    }
}

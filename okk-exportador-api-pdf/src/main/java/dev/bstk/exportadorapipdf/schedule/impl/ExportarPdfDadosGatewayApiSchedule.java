package dev.bstk.exportadorapipdf.schedule.impl;

import dev.bstk.exportadorapipdf.domain.service.ExportarPdfDadosGatewayApiService;
import dev.bstk.exportadorapipdf.schedule.OkkSchedule;
import io.quarkus.scheduler.Scheduled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
class ExportarPdfDadosGatewayApiSchedule implements OkkSchedule {

    private static final Logger LOG = LoggerFactory.getLogger(ExportarPdfDadosGatewayApiSchedule.class);

    @Inject
    protected ExportarPdfDadosGatewayApiService exportarPdfDadosGatewayApiService;

    @Override
    @Scheduled(every = "4s")
    public void executar() {
        LOG.info("Executando ExportarPdfDadosGatewayApiSchedule ...");
        exportarPdfDadosGatewayApiService.exportar();
    }
}

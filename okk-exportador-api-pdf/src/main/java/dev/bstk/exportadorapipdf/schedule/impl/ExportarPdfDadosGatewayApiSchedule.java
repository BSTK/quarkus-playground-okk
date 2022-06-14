package dev.bstk.exportadorapipdf.schedule.impl;

import dev.bstk.exportadorapipdf.schedule.OkkSchedule;
import io.quarkus.scheduler.Scheduled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
class ExportarPdfDadosGatewayApiSchedule implements OkkSchedule {

    private static final Logger LOG = LoggerFactory.getLogger(ExportarPdfDadosGatewayApiSchedule.class);

    @Override
    @Scheduled(every = "4s")
    public void executar() {
        LOG.info("Executando ExportarPdfDadosGatewayApiSchedule ...");
    }
}

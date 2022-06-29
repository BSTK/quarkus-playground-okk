package dev.bstk.exportadorapipdf.schedule.impl;

import dev.bstk.exportadorapipdf.domain.service.ReprocessarDadosGatwayApiService;
import dev.bstk.exportadorapipdf.schedule.OkkSchedule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ReprocessarDadosGatwayApiSchedule implements OkkSchedule {

    private static final Logger LOG = LoggerFactory.getLogger(ReprocessarDadosGatwayApiSchedule.class);

    @Inject
    protected ReprocessarDadosGatwayApiService reprocessarDadosGatwayApiService;

    @Override
    // @Scheduled(every = "10s")
    public void executar() {
        LOG.info("Executando ReprocessarDadosGatwayApiSchedule ...");
        reprocessarDadosGatwayApiService.reprocessar();
    }
}

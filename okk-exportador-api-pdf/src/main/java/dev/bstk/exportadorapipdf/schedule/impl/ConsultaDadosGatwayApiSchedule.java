package dev.bstk.exportadorapipdf.schedule.impl;

import dev.bstk.exportadorapipdf.domain.service.ConsultarDadosApiGatewayService;
import dev.bstk.exportadorapipdf.schedule.OkkSchedule;
import io.quarkus.scheduler.Scheduled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ConsultaDadosGatwayApiSchedule implements OkkSchedule {

    private static final Logger LOG = LoggerFactory.getLogger(ConsultaDadosGatwayApiSchedule.class);

    @Inject
    protected ConsultarDadosApiGatewayService consultarDadosApiGatewayService;

    @Override
    @Scheduled(every = "8s")
    public void executar() {
        LOG.info("Executando ConsultaDadosGatwayApiSchedule ...");
        consultarDadosApiGatewayService.consultarDados();
    }
}

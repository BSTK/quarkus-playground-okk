package dev.bstk.exportadorapipdf.schedule.impl;

import dev.bstk.exportadorapipdf.domain.service.ConsultarDadosApiGatewayService;
import dev.bstk.exportadorapipdf.schedule.OkkSchedule;
import io.quarkus.scheduler.Scheduled;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ConsultaDadosGatwayApiSchedule implements OkkSchedule {

    @Inject
    protected ConsultarDadosApiGatewayService consultarDadosApiGatewayService;

    @Override
    @Scheduled(every = "2s")
    public void executar() {
        consultarDadosApiGatewayService.consultarDados();
    }
}

package dev.bstk.exportadorapipdf.schedule.impl;

import dev.bstk.exportadorapipdf.schedule.OkkSchedule;
import io.quarkus.scheduler.Scheduled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ConsultaDadosGatwayApiSchedule implements OkkSchedule {

    private static final Logger LOG = LoggerFactory.getLogger(ConsultaDadosGatwayApiSchedule.class);

    @Override
    @Scheduled(every = "8s")
    public void executar() {
        LOG.info("Executando ConsultaDadosGatwayApiSchedule ...");
    }
}

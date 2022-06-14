package dev.bstk.exportadorapipdf.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ExportarPdfService {

    private static final Logger LOG = LoggerFactory.getLogger(ExportarPdfService.class);

    public void executar() {
        LOG.info("Exportando dados PDF");
    }
}

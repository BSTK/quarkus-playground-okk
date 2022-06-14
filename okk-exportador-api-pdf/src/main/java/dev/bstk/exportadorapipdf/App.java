package dev.bstk.exportadorapipdf;

import dev.bstk.exportadorapipdf.domain.ExportarPdfService;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.concurrent.TimeUnit;

@QuarkusMain
public class App implements QuarkusApplication {

    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    @Inject
    protected ExportarPdfService exportarPdfService;

    @Override
    public int run(String... args) throws Exception {
        LOG.info("Executando Exportador Api PDF ...");

        exportarPdfService.executar();
        TimeUnit.MILLISECONDS.sleep(5_000);

        exportarPdfService.executar();
        TimeUnit.MILLISECONDS.sleep(5_000);

        return 0;
    }
}

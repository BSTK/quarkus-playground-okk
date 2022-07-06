package dev.bstk.exportadorapipdf.domain.model.genius;

import dev.bstk.exportadorapipdf.domain.model.ConteudoPdf;
import dev.bstk.exportadorapipdf.domain.model.ConteudoPdfStatus;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class GeniusEndpointSearchConteudoPdfRepository implements PanacheRepository<ConteudoPdf> {

    public List<ConteudoPdf> pdfsParaExportar() {
        return list("status", ConteudoPdfStatus.PARA_PROCESSAR);
    }
}

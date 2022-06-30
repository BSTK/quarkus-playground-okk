package dev.bstk.exportadorapipdf.domain.model.genius;

import dev.bstk.exportadorapipdf.domain.model.ConteudoPdf;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class GeniusEndpointSearchConteudoPdfRepository implements PanacheRepository<ConteudoPdf> {

    public List<ConteudoPdf> pdfsParaExportar() {
        return list("status = :status", Parameters.with("status", null));
    }
}

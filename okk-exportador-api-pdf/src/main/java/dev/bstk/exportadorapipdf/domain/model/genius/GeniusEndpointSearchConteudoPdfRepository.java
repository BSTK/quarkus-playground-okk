package dev.bstk.exportadorapipdf.domain.model.genius;

import dev.bstk.exportadorapipdf.domain.model.ConteudoPdf;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GeniusEndpointSearchConteudoPdfRepository
    implements PanacheRepository<ConteudoPdf<GeniusEndpointSearchConteudoPdf>> { }

package dev.bstk.exportadorapipdf.domain.parser;

import dev.bstk.exportadorapipdf.gateway.response.ConsultaApiResponse;

public interface ConteudoPdfParser<T> {

    T pdf(final ConsultaApiResponse response);

}

package dev.bstk.exportadorapipdf.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/// TODO: REFATORAR PARA BIBLIOTECA OKK-UTILS
public final class JSONHelper {

    private static final ObjectMapper MAPPER = new ObjectMapper()
        .registerModule(new JavaTimeModule());

    private JSONHelper() { }

    public static ObjectMapper mapper() {
        return MAPPER;
    }
}

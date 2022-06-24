package dev.bstk.exportadorapipdf.helper;

import com.fasterxml.jackson.databind.ObjectMapper;

/// TODO: REFATORAR PARA BIBLIOTECA OKK-UTILS
public final class JSONHelper {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private JSONHelper() { }

    public static ObjectMapper mapper() {
        return MAPPER;
    }
}

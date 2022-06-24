package dev.bstk.exportadorapipdf.helper;

import com.fasterxml.jackson.databind.ObjectMapper;

public final class JSONHelper {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private JSONHelper() { }

    public static ObjectMapper mapper() {
        return MAPPER;
    }
}

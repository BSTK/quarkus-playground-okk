package dev.bstk.gatwayapi.domain.helper;

import java.util.Objects;

public final class StringHelper {

    private StringHelper() { }

    public static boolean isEmpty(final String string) {
        return Objects.isNull(string) || string.isEmpty();
    }

    public static boolean isNotEmpty(final String string) {
        return !isEmpty(string);
    }
}

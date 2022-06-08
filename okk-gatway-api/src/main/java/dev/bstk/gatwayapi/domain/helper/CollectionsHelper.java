package dev.bstk.gatwayapi.domain.helper;

import java.util.Map;
import java.util.Objects;

public final class CollectionsHelper {

    private CollectionsHelper() { }

    public static boolean isEmpty(final Map<?, ?> map) {
        return Objects.isNull(map) || map.isEmpty();
    }

    public static boolean isNotEmpty(final Map<?, ?> map) {
        return !isEmpty(map);
    }

    public static Map<?, ?> map(final Map<?, ?> map) {
        if (Objects.isNull(map)) {
            throw new IllegalArgumentException("Map nulo!");
        }

        return map;
    }
}

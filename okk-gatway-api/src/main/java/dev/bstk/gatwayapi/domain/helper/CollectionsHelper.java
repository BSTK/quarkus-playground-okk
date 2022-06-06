package dev.bstk.gatwayapi.domain.helper;

import java.util.Map;
import java.util.Objects;

public class CollectionsHelper {

    private CollectionsHelper() { }

    public static boolean isEmpty(final Map<?, ?> map) {
        return Objects.isNull(map) || map.isEmpty();
    }

    public static boolean isNotEmpty(final Map<?, ?> map) {
        return !isEmpty(map);
    }
}

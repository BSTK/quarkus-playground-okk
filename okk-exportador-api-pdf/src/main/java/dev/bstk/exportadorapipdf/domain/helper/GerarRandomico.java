package dev.bstk.exportadorapipdf.domain.helper;

import java.security.SecureRandom;
import java.util.Collection;
import java.util.Objects;

public final class GerarRandomico {

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    private GerarRandomico() { }

    public static int inteiro() {
        return SECURE_RANDOM.nextInt();
    }

    public static int inteiro(final Collection<?> collection) {
        final Collection<?> collectionValidada = Objects
            .requireNonNull(collection, "GerarRandomico: Lista de dados não pode ser Nula!");
        return SECURE_RANDOM.nextInt(collectionValidada.size());
    }
}

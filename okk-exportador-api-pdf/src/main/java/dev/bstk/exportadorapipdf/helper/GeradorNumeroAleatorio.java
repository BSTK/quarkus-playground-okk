package dev.bstk.exportadorapipdf.helper;

import java.util.Random;

/// TODO: REFATORAR PARA BIBLIOTECA OKK-UTILS
public final class GeradorNumeroAleatorio {

    private static final Random RANDOM = new Random();

    private GeradorNumeroAleatorio() { }

    public static int get() {
        return RANDOM.nextInt();
    }

    public static int get(int range) {
        return RANDOM.nextInt(range);
    }
}

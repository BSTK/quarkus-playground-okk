package dev.bstk.gatwayapi.domain.helper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class StringHelperTest {

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Deve retonar [ TRUE ] para String nulo/vazio [ IsEmpty ]")
    void deveRetonarTrueParaStringaNuloVazio(final String string) {
        Assertions.assertTrue(StringHelper.isEmpty(string));
    }

    @ParameterizedTest
    @ValueSource(strings = { "aa", "TT", "s" })
    @DisplayName("Deve retonar [ FALSE ] para String valida")
    void deveRetonarFalseParaStringValida(final String string) {
        Assertions.assertFalse(StringHelper.isEmpty(string));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Deve retonar [ FALSE ] para String nulo/vazio [ IsNotEmpty ]")
    void deveRetonarFalseParaStringNuloVazioIsNotEmpty(final String string) {
        Assertions.assertFalse(StringHelper.isNotEmpty(string));
    }

    @ParameterizedTest
    @ValueSource(strings = { "aa", "TT", "s" })
    @DisplayName("Deve retonar [ TRUE ] para String valida")
    void deveRetonarTrueParaStringComDados(final String string) {
        Assertions.assertTrue(StringHelper.isNotEmpty(string));
    }
}

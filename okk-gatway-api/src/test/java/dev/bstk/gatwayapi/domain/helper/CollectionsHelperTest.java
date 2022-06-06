package dev.bstk.gatwayapi.domain.helper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.Map;
import java.util.stream.Stream;

class CollectionsHelperTest {

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Deve retonar [ TRUE ] para Map nulo/vazio [ IsEmpty ]")
    void deveRetonarTrueParaMapNuloVazioIsEmpty(final Map<?, ?> map) {
        Assertions.assertTrue(CollectionsHelper.isEmpty(map));
    }

    @ParameterizedTest
    @MethodSource("dadosTestMap")
    @DisplayName("Deve retonar [ FALSE ] para Map nulo/vazio [ IsEmpty ]")
    void deveRetonarFalseParaMapComDadosIsEmpty(final Map<?, ?> map) {
        Assertions.assertFalse(CollectionsHelper.isEmpty(map));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Deve retonar [ TRUE ] para Map nulo/vazio [ IsNotEmpty ]")
    void deveRetonarTrueParaMapNuloVazioIsNotEmpty(final Map<?, ?> map) {
        Assertions.assertFalse(CollectionsHelper.isNotEmpty(map));
    }

    @ParameterizedTest
    @MethodSource("dadosTestMap")
    @DisplayName("Deve retonar [ FALSE ] para Map nulo/vazio [ IsNotEmpty ]")
    void deveRetonarFalseParaMapComDadosIsNotEmpty(final Map<?, ?> map) {
        Assertions.assertTrue(CollectionsHelper.isNotEmpty(map));
    }

    private static Stream<Arguments> dadosTestMap() {
        return Stream.of(
            Arguments.of(Map.of("chave", "valor")),
            Arguments.of(Map.of(new Object(), new Object())),
            Arguments.of(Map.of(1, 1)),
            Arguments.of(Map.of(1L, 1L)),
            Arguments.of(Map.of(1d, 1d))
        );
    }
}

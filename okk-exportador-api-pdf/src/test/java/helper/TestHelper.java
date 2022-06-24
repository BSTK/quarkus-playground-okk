package helper;

import dev.bstk.exportadorapipdf.helper.JSONHelper;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

/// TODO: REFATORAR PARA BIBLIOTECA OKK-UTILS
public final class TestHelper {

    private static final String SRC_TEST_RESOURCES = "src/test/resources";

    private TestHelper() { }

    public static  <T> T parse(final String arquivo, final Class<T> clazz) {
        try {
            validarCaminhoArquivoFixtureResource(arquivo);
            final var json = new File(SRC_TEST_RESOURCES + arquivo);
            return JSONHelper.mapper().readValue(json, clazz);
        } catch (IOException ex) {
            throw new IllegalArgumentException("Arquivos de dados das api's não foram encontrados!", ex);
        }
    }

    private static void validarCaminhoArquivoFixtureResource(final String arquivoResourceJson) {
        if (Objects.isNull(arquivoResourceJson)
            || arquivoResourceJson.isEmpty()
            || arquivoResourceJson.isBlank()) {
            throw new IllegalArgumentException("Caminho arquivo json não pode ser nulo ou vazio!");
        }

        if (Boolean.FALSE.equals(arquivoResourceJson.startsWith("/"))) {
            throw new IllegalArgumentException("Caminho arquivo json deve iniciar com: '/'!");
        }

        if (Boolean.FALSE.equals(arquivoResourceJson.endsWith(".json"))) {
            throw new IllegalArgumentException("Caminho arquivo json ter a extensão .json!");
        }
    }
}

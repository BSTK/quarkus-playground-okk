package dev.bstk.exportadorapipdf.domain.parser;

import dev.bstk.exportadorapipdf.helper.JSONHelper;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/// TODO: REFATORAR PARA USAR BIBLIOTECA OKK-UTILS
@ApplicationScoped
public class ConsultarDadosApiGatewayLeitorArquivosJson {

    private static final String SRC_MAIN_RESOURCES = "src/main/resources";

    @ConfigProperty(name = "exportadorpdf.arquivos.dados")
    protected String[] arquivosDadosJson;

    public <T> List<T> parse(final Class<T> clazz) {
        final List<T> arquivosParseados = new ArrayList<>();

        for (String arquivo : arquivosDadosJson) {
            try {
                validarCaminhoArquivoFixtureResource(arquivo);
                final var json = new File(SRC_MAIN_RESOURCES + arquivo);
                final var jsonParseado = JSONHelper.mapper().readValue(json, clazz);

                arquivosParseados.add(jsonParseado);
            } catch (IOException ex) {
                throw new IllegalArgumentException("Arquivos de dados das api's não foram encontrados!", ex);
            }
        }

        return arquivosParseados;
    }

    private void validarCaminhoArquivoFixtureResource(final String arquivoResourceJson) {
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

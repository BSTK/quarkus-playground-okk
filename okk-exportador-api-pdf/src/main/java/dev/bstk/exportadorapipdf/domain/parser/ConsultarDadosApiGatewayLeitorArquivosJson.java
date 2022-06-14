package dev.bstk.exportadorapipdf.domain.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class ConsultarDadosApiGatewayLeitorArquivosJson {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final String SRC_MAIN_RESOURCES = "src/main/resources";

    @ConfigProperty(name = "exportadorpdf.arquivos.dados")
    protected String[] arquivosDadosJson;

    public <T> List<T> parse(final Class<T> clazz) {
        final List<T> arquivosParseados = new ArrayList<>();

        for (String arquivo : arquivosDadosJson) {
            try {
                validarCaminhoArquivoFixtureResource(arquivo);
                final var json = new File(SRC_MAIN_RESOURCES + arquivo);
                final var jsonParseado = MAPPER.readValue(json, clazz);

                arquivosParseados.add(jsonParseado);
            } catch (IOException e) {
                e.printStackTrace();
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

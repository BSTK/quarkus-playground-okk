package dev.bstk.gatwayapi.domain.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import javax.ws.rs.client.Invocation;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ApisJaxRsHttpClientTest extends JaxRsHttpClient {


    @Test
    @DisplayName("Deve retonar um Jax Invocation.Builder com QueryParams e Headers")
    void deveRetonarUmJaxInvocationBuilderComQueryParamsEHeaders() {
        final Map<String, String> map = Map.of("chave_A", "valor_A", "chave_B", "valor_B");

        final Invocation.Builder requestBuild = ApisJaxRsHttpClient
            .Builder
            .builder()
            .url("https://mockurl.com.br")
            .queryParams(map)
            .headers(map)
            .build();

        Assertions.assertNotNull(requestBuild);

        Mockito.verify(webTarget, Mockito.times(map.size())).queryParam(anyString(), anyString());
        Mockito.verify(builder, Mockito.times(map.size())).header(anyString(), anyString());
    }

    @Test
    @DisplayName("Deve retonar um Jax Invocation.Builder com QueryParams")
    void deveRetonarUmJaxInvocationBuilderComQueryParams() {
        final Map<String, String> map = Map.of("chave_A", "valor_A", "chave_B", "valor_B");

        final Invocation.Builder requestBuild = ApisJaxRsHttpClient
            .Builder
            .builder()
            .url("https://mockurl.com.br")
            .queryParams(map)
            .build();

        Assertions.assertNotNull(requestBuild);

        Mockito.verify(webTarget, Mockito.times(map.size())).queryParam(anyString(), anyString());
        Mockito.verify(builder, Mockito.never()).header(anyString(), anyString());
    }

    @Test
    @DisplayName("Deve retonar um Jax Invocation.Builder com Headers")
    void deveRetonarUmJaxInvocationBuilderComHeaders() {
        final Map<String, String> map = Map.of("chave_A", "valor_A", "chave_B", "valor_B");

        final Invocation.Builder requestBuild = ApisJaxRsHttpClient
            .Builder
            .builder()
            .url("https://mockurl.com.br")
            .headers(map)
            .build();

        Assertions.assertNotNull(requestBuild);

        Mockito.verify(webTarget, Mockito.never()).queryParam(anyString(), anyString());
        Mockito.verify(builder, Mockito.times(map.size())).header(anyString(), anyString());
    }

    @Test
    @DisplayName("Deve lançar excesão de url invalida quando não setar a url")
    void deveLancarExcesaoDeUrlInvalidaAoNaoInformarUrl() {
        final ApisJaxRsHttpClient.Builder builder = ApisJaxRsHttpClient.Builder.builder();

        final Exception exception = Assertions
            .assertThrows(
                IllegalArgumentException.class,
                builder::build
            );

        Assertions.assertEquals("Url não pode ser nula ou vazia!", exception.getMessage());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Deve lançar excesão de url invalida [Nula / Vazia]")
    void deveLancarExcesaoDeUrlInvalida(final String url) {
        final ApisJaxRsHttpClient.Builder builder = ApisJaxRsHttpClient.Builder.builder();

        final Exception exception = Assertions
            .assertThrows(
                IllegalArgumentException.class,
                () -> builder.url(url)
            );

        Assertions.assertEquals("Url não pode ser nula ou vazia!", exception.getMessage());
    }
}

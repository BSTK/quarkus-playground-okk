package dev.bstk.gatwayapi.domain.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class ApisJaxRsHttpClientTest {

    @Mock
    private Client client;

    @Mock
    private WebTarget webTarget;

    @Mock
    private Invocation.Builder builder;

    private final MockedStatic<ClientBuilder> clientBuilderMock = Mockito.mockStatic(ClientBuilder.class);

    @BeforeEach
    void setUp() {
        clientBuilderMock
            .when(ClientBuilder::newClient)
            .thenReturn(client);

        clientBuilderMock
            .when(() -> ClientBuilder.newClient().target(anyString()))
            .thenReturn(webTarget);

        clientBuilderMock
            .when(() -> ClientBuilder.newClient().target(anyString()).request(MediaType.APPLICATION_JSON))
            .thenReturn(builder);
    }

    @AfterEach
    void down() {
        clientBuilderMock.close();
    }

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
}

package dev.bstk.gatwayapi.domain.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import static org.mockito.ArgumentMatchers.anyString;

abstract class JaxRsHttpClient {

    @Mock
    protected Client client;

    @Mock
    protected WebTarget webTarget;

    @Mock
    protected Invocation.Builder builder;

    protected final MockedStatic<ClientBuilder> clientBuilderMock = Mockito.mockStatic(ClientBuilder.class);

    @BeforeEach
    protected void setUp() {
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
    protected void down() {
        clientBuilderMock.close();
    }
}

package dev.bstk.gatwayapi.domain.service;

import dev.bstk.gatwayapi.resource.request.ConsultaApiItemRequest;
import dev.bstk.gatwayapi.resource.request.ConsultaApiRequest;
import dev.bstk.gatwayapi.resource.response.ConsultaApiResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class ApisServiceTest {

    @InjectMocks
    private ApisService apisService;

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

    @Test
    void deveRetornarDadosConsultaApiParaUmCasoDeSucessoOk() {
        mockResponse(
            Response
                .ok("OBJETO_CONTENDO_SUCESSO_OK")
                .status(Response.Status.OK)
                .build());

        final ConsultaApiRequest request = request();
        final ConsultaApiResponse response = apisService.consultar(request);

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getDados());
        Assertions.assertNotNull(response.getDataHoraRequest());

        Assertions.assertFalse(response.getDados().isEmpty());

        Assertions.assertEquals(response.getDados().size(), request.getApis().size());

        response.getDados().forEach(item -> {
            Assertions.assertInstanceOf(String.class, item.getResponse());
            Assertions.assertEquals("OBJETO_CONTENDO_SUCESSO_OK", item.getResponse());
        });
    }

    @Test
    void deveRetornarDadosConsultaApiParaUmCasoDeClientError() {
        mockResponse(Response.status(Response.Status.BAD_REQUEST).build());

        final ConsultaApiRequest request = request();
        final ConsultaApiResponse response = apisService.consultar(request);

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getDados());
        Assertions.assertNotNull(response.getDataHoraRequest());

        Assertions.assertFalse(response.getDados().isEmpty());

        Assertions.assertEquals(response.getDados().size(), request.getApis().size());

        response.getDados().forEach(item -> {
            Assertions.assertInstanceOf(String.class, item.getResponse());
            Assertions.assertEquals("OBJETO_CONTENDO_ERRO_CLIENTE", item.getResponse());
        });
    }

    @Test
    void deveRetornarDadosConsultaDeApiParaUmCasoDeServerError() {
        mockResponse(Response.status(Response.Status.INTERNAL_SERVER_ERROR).build());

        final ConsultaApiRequest request = request();
        final ConsultaApiResponse response = apisService.consultar(request);

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getDados());
        Assertions.assertNotNull(response.getDataHoraRequest());

        Assertions.assertFalse(response.getDados().isEmpty());

        Assertions.assertEquals(response.getDados().size(), request.getApis().size());

        response.getDados().forEach(item -> {
            Assertions.assertInstanceOf(String.class, item.getResponse());
            Assertions.assertEquals("OBJETO_CONTENDO_ERRO_SERVIDOR", item.getResponse());
        });
    }

    private void mockResponse(final Response response) {
        clientBuilderMock
            .when(() -> ClientBuilder
                .newClient()
                .target(anyString()).request(MediaType.APPLICATION_JSON)
                .get())
            .thenReturn(response);
    }

    private ConsultaApiRequest request() {
        final ConsultaApiItemRequest itemRequest = new ConsultaApiItemRequest();
        itemRequest.setUrl("https://mock-ok.com.br");
        itemRequest.setNomeApiExterna("Mock OK");

        final ConsultaApiRequest request = new ConsultaApiRequest();
        request.setApis(Collections.singletonList(itemRequest));
        request.setAppCliente("MOCK_CLIENTE");
        return request;
    }
}

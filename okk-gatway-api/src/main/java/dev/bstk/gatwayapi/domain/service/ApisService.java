package dev.bstk.gatwayapi.domain.service;

import dev.bstk.gatwayapi.domain.helper.CollectionsHelper;
import dev.bstk.gatwayapi.domain.service.dto.ApisAutenticadorAcessTokenDto;
import dev.bstk.gatwayapi.resource.request.ConsultaApiItemRequest;
import dev.bstk.gatwayapi.resource.request.ConsultaApiRequest;
import dev.bstk.gatwayapi.resource.request.ConsultaApiTokenRequest;
import dev.bstk.gatwayapi.resource.response.ConsultaApiDadosItemResponse;
import dev.bstk.gatwayapi.resource.response.ConsultaApiResponse;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@ApplicationScoped
public class ApisService {

    @Inject
    protected ApisAutenticadorService autenticadorService;


    public ConsultaApiResponse consultar(@Valid final ConsultaApiRequest apiRequest) {
        final List<ConsultaApiDadosItemResponse> consultaApiDados = new ArrayList<>();

        for (ConsultaApiItemRequest itemRequest : apiRequest.getApis()) {
            final ConsultaApiTokenRequest apiObterToken = itemRequest.getApiObterToken();

            if (Objects.nonNull(apiObterToken)) {
                final ApisAutenticadorAcessTokenDto acessToken = autenticadorService.obterToken(apiObterToken);

                if (CollectionsHelper.isNotEmpty(itemRequest.getHeaders())) {
                    itemRequest.getHeaders().put(HttpHeaders.AUTHORIZATION, acessToken.getAccessToken());
                } else {
                    itemRequest.setHeaders(Map.of(HttpHeaders.AUTHORIZATION, acessToken.getAccessToken()));
                }
            }

            final Invocation.Builder request = ApisJaxRsHttpClient
                .Builder
                .builder()
                .url(itemRequest.getUrl())
                .headers(itemRequest.getHeaders())
                .queryParams(itemRequest.getQueryParams())
                .build();

            final Response response = request.get();

            final ConsultaApiDadosItemResponse itemResponse = new ConsultaApiDadosItemResponse(itemRequest.getNomeApiExterna());
            itemResponse.setResponse(response.readEntity(Object.class));

            consultaApiDados.add(itemResponse);
        }

        return new ConsultaApiResponse(consultaApiDados);
    }
}

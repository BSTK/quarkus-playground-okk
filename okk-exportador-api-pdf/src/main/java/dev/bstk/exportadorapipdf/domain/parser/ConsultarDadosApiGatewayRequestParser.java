package dev.bstk.exportadorapipdf.domain.parser;

import dev.bstk.exportadorapipdf.domain.gateway.request.ConsultaApiItemRequest;
import dev.bstk.exportadorapipdf.domain.gateway.request.ConsultaApiRequest;
import dev.bstk.exportadorapipdf.domain.helper.GerarRandomico;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;

@ApplicationScoped
public class ConsultarDadosApiGatewayRequestParser {

    private final List<ConsultaApiRequest> requests;

    @Inject
    protected ConsultarDadosApiGatewayLeitorArquivosJson leitorArquivosJson;

    public ConsultarDadosApiGatewayRequestParser() {
        requests = leitorArquivosJson.parse(ConsultaApiRequest.class);
    }


    public ConsultaApiRequest request() {
        final ConsultaApiRequest request = requests.get(GerarRandomico.inteiro(requests));

        for (ConsultaApiItemRequest api : request.getApis()) {
            if (api.getQueryParams() == null) {
                api.setQueryParams(new HashMap<>());
            }

            aplicarRegraUrl(api);
            aplicarRegraQueryParams(api);
        }

        return request;
    }

    private void aplicarRegraUrl(final ConsultaApiItemRequest api) {
        if (api.getUrl().contains("{ID}")) {
            final String novaUrl = api.getUrl().replace("{ID}", "1");
            api.setUrl(novaUrl);
        }

        if (api.getUrl().endsWith("/search")) {
            api.getQueryParams().put("q", "GERAR NOME COM a lib Faker");
        }
    }

    private void aplicarRegraQueryParams(final ConsultaApiItemRequest api) {
        if (api.getQueryParams().containsKey("page")) {
            api.getQueryParams().put("page", String.valueOf(GerarRandomico.inteiro()));
        }

        if (api.getQueryParams().containsKey("per_page")) {
            api.getQueryParams().put("per_page", String.valueOf(GerarRandomico.inteiro()));
        }
    }
}

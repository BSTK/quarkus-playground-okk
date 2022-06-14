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

    private static final String PATH_PARAM_ID = "{ID}";
    private static final String PATH_PARAM_SEARCH = "search";

    private static final String QUERY_PARAM_Q = "q";
    private static final String QUERY_PARAM_PAGE = "page";
    private static final String QUERY_PARAM_PER_PAGE = "per_page";

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
        if (api.getUrl().contains(PATH_PARAM_ID)) {
            final String novaUrl = api.getUrl().replace(PATH_PARAM_ID, "1");
            api.setUrl(novaUrl);
        }

        if (api.getUrl().endsWith(PATH_PARAM_SEARCH)) {
            api.getQueryParams().put(QUERY_PARAM_Q, "GERAR NOME COM a lib Faker");
        }
    }

    private void aplicarRegraQueryParams(final ConsultaApiItemRequest api) {
        if (api.getQueryParams().containsKey(QUERY_PARAM_PAGE)) {
            api.getQueryParams().put(QUERY_PARAM_PAGE, String.valueOf(GerarRandomico.inteiro()));
        }

        if (api.getQueryParams().containsKey(QUERY_PARAM_PER_PAGE)) {
            api.getQueryParams().put(QUERY_PARAM_PER_PAGE, String.valueOf(GerarRandomico.inteiro()));
        }
    }
}

package dev.bstk.exportadorapipdf.domain.parser;

import com.github.javafaker.Faker;
import dev.bstk.exportadorapipdf.gateway.request.ConsultaApiItemRequest;
import dev.bstk.exportadorapipdf.gateway.request.ConsultaApiRequest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@ApplicationScoped
public class ConsultarDadosApiGatewayRequestParser {

    private static final String PATH_PARAM_ID = "{ID}";
    private static final String PATH_PARAM_SEARCH = "search";

    private static final String QUERY_PARAM_Q = "q";
    private static final String QUERY_PARAM_PAGE = "page";
    private static final String QUERY_PARAM_PER_PAGE = "per_page";

    private static final Faker FAKER = new Faker();
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    private static final String CACHE_CONSULTA_API_REQUEST_CHAVE = "CACHE_CONSULTA_API_REQUEST_CHAVE";
    private static final ConcurrentMap<String, List<ConsultaApiRequest>> CACHE = new ConcurrentHashMap<>();

    @Inject
    protected ConsultarDadosApiGatewayLeitorArquivosJson leitorArquivosJson;


    public ConsultaApiRequest request() {
        final List<ConsultaApiRequest> requests = obterDadosRequest();
        final ConsultaApiRequest request = requests.get(SECURE_RANDOM.nextInt(requests.size()));

        for (ConsultaApiItemRequest api : request.getApis()) {
            if (api.getQueryParams() == null) {
                api.setQueryParams(new HashMap<>());
            }

            aplicarRegraUrl(api);
            aplicarRegraQueryParams(api);
        }

        return request;
    }

    private List<ConsultaApiRequest> obterDadosRequest() {
        final List<ConsultaApiRequest> consultaApiRequestsCache = CACHE.get(CACHE_CONSULTA_API_REQUEST_CHAVE);
        if (Objects.nonNull(consultaApiRequestsCache)) {
            return consultaApiRequestsCache;
        }

        final List<ConsultaApiRequest> requests = leitorArquivosJson.parse(ConsultaApiRequest.class);
        CACHE.put(CACHE_CONSULTA_API_REQUEST_CHAVE, requests);

        return requests;
    }

    private void aplicarRegraUrl(final ConsultaApiItemRequest api) {
        if (api.getUrl().contains(PATH_PARAM_ID)) {
            final String novaUrl = api.getUrl().replace(PATH_PARAM_ID, FAKER.idNumber().valid());
            api.setUrl(novaUrl);
        }

        if (api.getUrl().endsWith(PATH_PARAM_SEARCH)) {
            api.getQueryParams().put(QUERY_PARAM_Q, FAKER.artist().name());
        }
    }

    private void aplicarRegraQueryParams(final ConsultaApiItemRequest api) {
        if (api.getQueryParams().containsKey(QUERY_PARAM_PAGE)) {
            api.getQueryParams().put(QUERY_PARAM_PAGE, String.valueOf(FAKER.number().randomDigitNotZero()));
        }

        if (api.getQueryParams().containsKey(QUERY_PARAM_PER_PAGE)) {
            api.getQueryParams().put(QUERY_PARAM_PER_PAGE, String.valueOf(FAKER.number().randomDigitNotZero()));
        }
    }
}

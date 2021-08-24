package com.medinar.covidwatch.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.medinar.covidwatch.config.CovidApiConfig;
import static com.medinar.covidwatch.constant.Constants.ALLWNULL_REQ_PARAM;
import static com.medinar.covidwatch.constant.Constants.CONTENT_TYPE;
import static com.medinar.covidwatch.constant.Constants.ENTRY_NOT_FOUND_ERROR;
import static com.medinar.covidwatch.constant.Constants.INTERNAL_SERVER_CODE;
import static com.medinar.covidwatch.constant.Constants.INTERNAL_SERVER_ERROR;
import static com.medinar.covidwatch.constant.Constants.NOT_FOUND_CODE;
import static com.medinar.covidwatch.constant.Constants.SORT_REQ_PARAM;
import static com.medinar.covidwatch.constant.Constants.STRICT_REQ_PARAM;
import static com.medinar.covidwatch.constant.Constants.TWODAYSAGO_REQ_PARAM;
import static com.medinar.covidwatch.constant.Constants.YESTERDAY_REQ_PARAM;
import com.medinar.covidwatch.domain.InternationalTotal;
import com.medinar.covidwatch.exception.InternationalCasesNotFoundException;
import com.medinar.covidwatch.utility.JSONUtils;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.stereotype.Service;

/**
 *
 * @author Rommel Medina
 */
@Slf4j
@Service
public class InternationalClientImpl extends AbstractClient implements InternationalClient {

    @Autowired
    CovidApiConfig config;

    @Override
    public InternationalTotal getInternationalTotal(
            String country,
            boolean yesterday,
            boolean twoDaysAgo,
            boolean strict,
            boolean allowNull
    ) throws InterruptedException, ExecutionException, IOException, InternationalCasesNotFoundException {

        StringBuilder sbTotalUrl = new StringBuilder(100);
        sbTotalUrl.append(config.getBaseUrl())
                .append(config.getInternationalResource())
                .append("/").append(country)
                .append(YESTERDAY_REQ_PARAM).append(yesterday)
                .append(TWODAYSAGO_REQ_PARAM).append(twoDaysAgo)
                .append(STRICT_REQ_PARAM).append(strict)
                .append(ALLWNULL_REQ_PARAM).append(allowNull);

        HttpRequest request = HttpRequest
                .newBuilder(URI.create(sbTotalUrl.toString()))
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .GET()
                .build();

        CompletableFuture<HttpResponse<String>> response = HTTP_CLIENT
                .sendAsync(request, HttpResponse.BodyHandlers.ofString());

        response.thenAccept(res -> log.info(res.toString()));

        InternationalTotal internationalTotal;
        switch (response.get().statusCode()) {
            case INTERNAL_SERVER_CODE:
                log.error(INTERNAL_SERVER_ERROR);
                throw new InternationalCasesNotFoundException(
                        "International totals not available"
                );
            case NOT_FOUND_CODE:
                log.error("Country not found: " + country);
                throw new InternationalCasesNotFoundException(
                        "Country not found: " + country
                );
            default:
                internationalTotal = JSONUtils.covertFromJsonToObject(
                        response.get().body(),
                        InternationalTotal.class
                );
                log.info(internationalTotal.toString());
                break;
        }
        response.join();

        return internationalTotal;
    }

    @Override
    public List<InternationalTotal> getInternationalTotalsbyContinent(
            String continent,
            boolean yesterday,
            boolean twoDaysAgo,
            boolean strict,
            boolean allowNull
    ) throws InterruptedException, ExecutionException, IOException {

        StringBuilder sbContinentalTotalUrl = new StringBuilder(100);
        sbContinentalTotalUrl.append(config.getBaseUrl())
                .append(config.getInternationalResource())
                .append(YESTERDAY_REQ_PARAM).append(yesterday)
                .append(TWODAYSAGO_REQ_PARAM).append(twoDaysAgo)
                .append(STRICT_REQ_PARAM).append(strict)
                .append(ALLWNULL_REQ_PARAM).append(allowNull);

        HttpRequest request = HttpRequest
                .newBuilder(URI.create(sbContinentalTotalUrl.toString()))
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .GET()
                .build();

        CompletableFuture<HttpResponse<String>> response = HTTP_CLIENT
                .sendAsync(request, HttpResponse.BodyHandlers.ofString());

        response.thenAccept(res -> log.info(res.toString()));

        List<InternationalTotal> internationalTotals = JSONUtils
                .convertFromJsonToList(response.get().body(),
                        new TypeReference<List<InternationalTotal>>() {
                });

        List<InternationalTotal> internationalTotalsByContinent = null;

        switch (response.get().statusCode()) {
            case INTERNAL_SERVER_CODE:
                log.error(INTERNAL_SERVER_ERROR);
                break;
            case NOT_FOUND_CODE:
                log.error(ENTRY_NOT_FOUND_ERROR);
                break;
            default:
                internationalTotalsByContinent = internationalTotals.stream()
                        .filter(internationalTotal -> internationalTotal
                        .getContinent()
                        .equalsIgnoreCase(continent)
                        )
                        .collect(Collectors.toList());
                internationalTotalsByContinent.forEach(
                        total -> log.info(total.toString())
                );
                break;
        }
        response.join();
        return internationalTotalsByContinent;
    }

    @Override
    public List<InternationalTotal> getInternationalTotals(
            boolean yesterday,
            boolean twoDaysAgo,
            String sortBy,
            boolean allowNull
    ) throws InterruptedException, ExecutionException, IOException {

        StringBuilder sbInternationalTotalUrl = new StringBuilder(100);
        sbInternationalTotalUrl.append(config.getBaseUrl())
                .append(config.getInternationalResource())
                .append(YESTERDAY_REQ_PARAM).append(yesterday)
                .append(TWODAYSAGO_REQ_PARAM).append(twoDaysAgo);
        if (!sortBy.isBlank()) {
            sbInternationalTotalUrl.append(SORT_REQ_PARAM).append(sortBy);
        }
        sbInternationalTotalUrl.append(ALLWNULL_REQ_PARAM).append(allowNull);

        HttpRequest request = HttpRequest
                .newBuilder(URI.create(sbInternationalTotalUrl.toString()))
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .GET()
                .build();

        CompletableFuture<HttpResponse<String>> response = HTTP_CLIENT
                .sendAsync(request, HttpResponse.BodyHandlers.ofString());

        response.thenAccept(res -> log.info(res.toString()));

        List<InternationalTotal> internationalTotals = null;

        switch (response.get().statusCode()) {
            case INTERNAL_SERVER_CODE:
                log.error(INTERNAL_SERVER_ERROR);
                break;
            case NOT_FOUND_CODE:
                log.error(ENTRY_NOT_FOUND_ERROR);
                break;
            default:
                internationalTotals = JSONUtils
                        .convertFromJsonToList(response.get().body(),
                                new TypeReference<List<InternationalTotal>>() {
                        });
                internationalTotals.forEach(total -> log.info(total.toString()));
                break;
        }
        response.join();
        return internationalTotals;
    }

}

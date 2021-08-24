package com.medinar.covidwatch.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.medinar.covidwatch.config.CovidApiConfig;
import static com.medinar.covidwatch.constant.Constants.ALLWNULL_REQ_PARAM;
import static com.medinar.covidwatch.constant.Constants.CONTENT_TYPE;
import static com.medinar.covidwatch.constant.Constants.INTERNAL_SERVER_CODE;
import static com.medinar.covidwatch.constant.Constants.INTERNAL_SERVER_ERROR;
import static com.medinar.covidwatch.constant.Constants.STRICT_REQ_PARAM;
import static com.medinar.covidwatch.constant.Constants.TWODAYSAGO_REQ_PARAM;
import static com.medinar.covidwatch.constant.Constants.YESTERDAY_REQ_PARAM;
import com.medinar.covidwatch.domain.ContinentalTotal;
import com.medinar.covidwatch.exception.ContinentNotFoundException;
import com.medinar.covidwatch.utility.JSONUtils;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
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
public class ContinentalClientImpl extends AbstractClient implements ContinentalClient {

    @Autowired
    CovidApiConfig config;

    @Override
    public Optional<ContinentalTotal> getContinentalTotal(
            String continent,
            boolean yesterday,
            boolean twoDaysAgo,
            boolean strict,
            boolean allowNull
    ) throws InterruptedException, ExecutionException, IOException, ContinentNotFoundException {

        StringBuilder sbContinentalTotalUrl = new StringBuilder(100);
        sbContinentalTotalUrl.append(config.getBaseUrl())
                .append(config.getContinentalResource())
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

        List<ContinentalTotal> continentalTotals = JSONUtils
                .convertFromJsonToList(
                        response.get().body(),
                        new TypeReference<List<ContinentalTotal>>() {
                });

        Optional<ContinentalTotal> continentalTotal;
        if (response.get().statusCode() == INTERNAL_SERVER_CODE) {
            log.error(INTERNAL_SERVER_ERROR);
            throw new ContinentNotFoundException("Continental Totals not Available");
        } else {
            continentalTotal = continentalTotals.stream()
                    .filter(ct -> ct.getContinent().equalsIgnoreCase(continent))
                    .findFirst();
        }

        response.join();

        return continentalTotal;
    }

    @Override
    public List<ContinentalTotal> getContinentalTotals(
            boolean yesterday,
            boolean twoDaysAgo,
            boolean strict,
            boolean allowNull
    ) throws InterruptedException, ExecutionException, IOException {

        StringBuilder sbContinentalTotalUrl = new StringBuilder(100);
        sbContinentalTotalUrl.append(config.getBaseUrl())
                .append(config.getContinentalResource())
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

        List<ContinentalTotal> continentalTotals = JSONUtils
                .convertFromJsonToList(
                        response.get().body(),
                        new TypeReference<List<ContinentalTotal>>() {
                });

        if (response.get().statusCode() == INTERNAL_SERVER_CODE) {
            log.error(INTERNAL_SERVER_ERROR);
            // TOD: Throw error here
        } else {
            continentalTotals.forEach(total -> log.info(total.toString()));
        }

        response.join();
        return continentalTotals;
    }

}

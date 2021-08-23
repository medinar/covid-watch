package com.medinar.covidwatch.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.medinar.covidwatch.config.CovidApiConfig;
import static com.medinar.covidwatch.constant.Constants.ALLWNULL_REQ_PARAM;
import static com.medinar.covidwatch.constant.Constants.CONTENT_TYPE;
import static com.medinar.covidwatch.constant.Constants.ENTRY_NOT_FOUND_ERROR;
import static com.medinar.covidwatch.constant.Constants.INTERNAL_SERVER_ERROR;
import static com.medinar.covidwatch.constant.Constants.STRICT_REQ_PARAM;
import static com.medinar.covidwatch.constant.Constants.TWODAYSAGO_REQ_PARAM;
import static com.medinar.covidwatch.constant.Constants.YESTERDAY_REQ_PARAM;
import com.medinar.covidwatch.domain.ContinentalTotal;
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
    ) throws InterruptedException, ExecutionException, IOException {

        StringBuilder sbContinentalTotalUrl = new StringBuilder(100);
        sbContinentalTotalUrl.append(config.getBaseUrl())
                .append(config.getContinentalResource())
                .append("?yesterday=").append(yesterday)
                .append("&twoDaysAgo=").append(twoDaysAgo)
                .append("&strict=").append(strict)
                .append("&allowNull=").append(allowNull);

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

        Optional<ContinentalTotal> continentalTotal = Optional.empty();
        if (response.get().statusCode() == 500) {
            log.error(INTERNAL_SERVER_ERROR);
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

        switch (response.get().statusCode()) {
            case 500:
                log.error(INTERNAL_SERVER_ERROR);
                break;
            case 400:
                log.error(ENTRY_NOT_FOUND_ERROR);
                break;
            default:
                continentalTotals.forEach(total -> log.info(total.toString()));
                break;
        }
        response.join();
        return continentalTotals;
    }

}

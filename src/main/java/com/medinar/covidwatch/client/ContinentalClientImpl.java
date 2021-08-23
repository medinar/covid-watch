package com.medinar.covidwatch.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.medinar.covidwatch.config.CovidApiConfig;
import static com.medinar.covidwatch.constant.Constants.CONTENT_TYPE;
import static com.medinar.covidwatch.constant.Constants.INTERNAL_SERVER_ERROR;
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
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.stereotype.Service;

/**
 *
 * @author Rommel Medina
 */
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

        response.thenAccept(res -> System.out.println(res));

        List<ContinentalTotal> continentalTotals = JSONUtils
                .convertFromJsonToList(
                        response.get().body(),
                        new TypeReference<List<ContinentalTotal>>() {
                });

        Optional<ContinentalTotal> continentalTotal = Optional.empty();
        if (response.get().statusCode() == 500) {
            System.out.println(INTERNAL_SERVER_ERROR);
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

        response.thenAccept(res -> System.out.println(res));

        List<ContinentalTotal> continentalTotals = JSONUtils
                .convertFromJsonToList(
                        response.get().body(),
                        new TypeReference<List<ContinentalTotal>>() {
                });

        if (response.get().statusCode() == 500) {
            System.out.println("Continental Totals Not Avaialble");
        } else {
            continentalTotals.forEach(System.out::println);
        }
        response.join();
        return continentalTotals;
    }

}

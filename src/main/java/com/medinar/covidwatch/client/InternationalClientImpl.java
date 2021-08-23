package com.medinar.covidwatch.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.medinar.covidwatch.config.CovidApiConfig;
import com.medinar.covidwatch.domain.InternationalTotal;
import com.medinar.covidwatch.utility.JSONUtils;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.stereotype.Service;

/**
 *
 * @author Rommel Medina
 */
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
    ) throws InterruptedException, ExecutionException, IOException {

        StringBuilder sbTotalUrl = new StringBuilder(100);
        sbTotalUrl.append(config.getBaseUrl())
                .append(config.getInternationalResource())
                .append("/").append(country)
                .append("?yesterday=").append(yesterday)
                .append("&twoDaysAgo=").append(twoDaysAgo)
                .append("&strict=").append(strict)
                .append("&allowNull=").append(allowNull);

        HttpRequest request = HttpRequest
                .newBuilder(URI.create(sbTotalUrl.toString()))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        CompletableFuture<HttpResponse<String>> response = HTTP_CLIENT
                .sendAsync(request, HttpResponse.BodyHandlers.ofString());

        response.thenAccept(res -> System.out.println(res));

        InternationalTotal internationalTotal = null;
        switch (response.get().statusCode()) {
            case 500:
                System.out.println("Country Not Avaialble");
                break;
            case 404:
                System.out.println("Country not found");
                break;
            default:
                internationalTotal = JSONUtils.covertFromJsonToObject(
                        response.get().body(),
                        InternationalTotal.class
                );
                System.out.println(internationalTotal);
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
                .append("?yesterday=").append(yesterday)
                .append("&twoDaysAgo=").append(twoDaysAgo)
                .append("&strict=").append(strict)
                .append("&allowNull=").append(allowNull);

        HttpRequest request = HttpRequest
                .newBuilder(URI.create(sbContinentalTotalUrl.toString()))
                .header("Content-Type", APPLICATION_JSON_VALUE)
                .GET()
                .build();

        CompletableFuture<HttpResponse<String>> response = HTTP_CLIENT
                .sendAsync(request, HttpResponse.BodyHandlers.ofString());

        response.thenAccept(res -> System.out.println(res));

        List<InternationalTotal> internationalTotals = JSONUtils
                .convertFromJsonToList(response.get().body(),
                        new TypeReference<List<InternationalTotal>>() {
                });

        List<InternationalTotal> internationalTotalsByContinent = null;

        if (response.get().statusCode() == 500) {
            System.out.println("Country Totals Not Available");
        } else {

            internationalTotalsByContinent = internationalTotals.stream()
                    .filter(internationalTotal -> internationalTotal
                    .getContinent()
                    .equalsIgnoreCase(continent)
                    )
                    .collect(Collectors.toList());

            internationalTotalsByContinent.forEach(System.out::println);
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
                .append("?yesterday=").append(yesterday)
                .append("&twoDaysAgo=").append(twoDaysAgo);
        if (!sortBy.isBlank()) {
            sbInternationalTotalUrl.append("&sort=").append(sortBy);
        }
        sbInternationalTotalUrl.append("&allowNull=").append(allowNull);

        HttpRequest request = HttpRequest
                .newBuilder(URI.create(sbInternationalTotalUrl.toString()))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        CompletableFuture<HttpResponse<String>> response = HTTP_CLIENT
                .sendAsync(request, HttpResponse.BodyHandlers.ofString());

        response.thenAccept(res -> System.out.println(res));

        List<InternationalTotal> internationalTotals = null;

        if (response.get().statusCode() == 500) {
            System.out.println("Country Totals Not Avaialble");
        } else {
            internationalTotals = JSONUtils
                    .convertFromJsonToList(response.get().body(),
                            new TypeReference<List<InternationalTotal>>() {
                    });
            internationalTotals.forEach(System.out::println);
        }
        response.join();
        return internationalTotals;
    }

}

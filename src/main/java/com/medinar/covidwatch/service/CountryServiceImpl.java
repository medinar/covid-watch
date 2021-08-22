package com.medinar.covidwatch.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.medinar.covidwatch.domain.CountryTotal;
import static com.medinar.covidwatch.service.AbstractService.COUNTRIES_TOTAL_URL;
import static com.medinar.covidwatch.service.AbstractService.COUNTRY_TOTAL_URL;
import static com.medinar.covidwatch.service.AbstractService.HTTP_CLIENT;
import com.medinar.covidwatch.utility.JSONUtils;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 *
 * @author Rommel Medina
 */
@Service
public class CountryServiceImpl implements CountryService {

    @Override
    public List<CountryTotal> getTotals(
            boolean yesterday,
            boolean twoDaysAgo,
            String sortBy,
            boolean allowNull
    ) throws InterruptedException, ExecutionException, IOException {

        StringBuilder sbCountryTotalUrl = new StringBuilder(100);
        sbCountryTotalUrl.append(COUNTRIES_TOTAL_URL)
                .append("?yesterday=").append(yesterday)
                .append("&twoDaysAgo=").append(twoDaysAgo);
        if (!sortBy.isBlank()) {
            sbCountryTotalUrl.append("&sort=").append(sortBy);
        }
        sbCountryTotalUrl.append("&allowNull=").append(allowNull);

        HttpRequest request = HttpRequest
                .newBuilder(URI.create(sbCountryTotalUrl.toString()))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        CompletableFuture<HttpResponse<String>> response = HTTP_CLIENT
                .sendAsync(request, HttpResponse.BodyHandlers.ofString());

        response.thenAccept(res -> System.out.println(res));

        List<CountryTotal> countryTotals = JSONUtils
                .convertFromJsonToList(
                        response.get().body(),
                        new TypeReference<List<CountryTotal>>() {
                });

        if (response.get().statusCode() == 500) {
            System.out.println("Country Totals Not Avaialble");
        } else {
            countryTotals.forEach(System.out::println);
        }
        response.join();
        return countryTotals;
    }

    @Override
    public CountryTotal getTotal(
            String country,
            boolean yesterday,
            boolean twoDaysAgo,
            String sortBy,
            boolean allowNull
    ) throws InterruptedException, ExecutionException, IOException {

        StringBuilder sbTotalUrl = new StringBuilder(100);
        sbTotalUrl.append(COUNTRY_TOTAL_URL)
                .append("/").append(country)
                .append("?yesterday=").append(yesterday)
                .append("&twoDaysAgo=").append(twoDaysAgo);
        if (!sortBy.isBlank()) {
            sbTotalUrl.append("&sort=").append(sortBy);
        }
        sbTotalUrl.append("&allowNull=").append(allowNull);

        HttpRequest request = HttpRequest
                .newBuilder(URI.create(sbTotalUrl.toString()))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        CompletableFuture<HttpResponse<String>> response = HTTP_CLIENT
                .sendAsync(request, HttpResponse.BodyHandlers.ofString());

        response.thenAccept(res -> System.out.println(res));

        CountryTotal countryTotal = null;
        if (response.get().statusCode() == 500) {
            System.out.println("Country Not Avaialble");
        } else {
            countryTotal = JSONUtils.covertFromJsonToObject(response.get().body(), CountryTotal.class);
            System.out.println(countryTotal);
        }
        response.join();
        return countryTotal;
    }

    @Override
    public List<CountryTotal> getTotalsbyContinent(
            String continent,
            boolean yesterday,
            boolean twoDaysAgo,
            String sortBy,
            boolean allowNull
    ) throws InterruptedException, ExecutionException, IOException {

        StringBuilder sbContinentalTotalUrl = new StringBuilder(100);
        sbContinentalTotalUrl.append(COUNTRIES_TOTAL_URL)
                .append("?yesterday=").append(yesterday)
                .append("&twoDaysAgo=").append(twoDaysAgo);
        if (!sortBy.isBlank()) {
            sbContinentalTotalUrl.append("&sort=").append(sortBy);
        }
        sbContinentalTotalUrl.append("&allowNull=").append(allowNull);

        HttpRequest request = HttpRequest
                .newBuilder(URI.create(sbContinentalTotalUrl.toString()))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        CompletableFuture<HttpResponse<String>> response = HTTP_CLIENT
                .sendAsync(request, HttpResponse.BodyHandlers.ofString());

        response.thenAccept(res -> System.out.println(res));

        List<CountryTotal> countryTotals = JSONUtils
                .convertFromJsonToList(
                        response.get().body(),
                        new TypeReference<List<CountryTotal>>() {
                });

        List<CountryTotal> countryTotalsByContinent = null;

        if (response.get().statusCode() == 500) {
            System.out.println("Country Totals Not Available");
        } else {

            countryTotalsByContinent = countryTotals.stream()
                    .filter(countryTotal -> countryTotal
                    .getContinent()
                    .equalsIgnoreCase(continent)
                    )
                    .collect(Collectors.toList());

            countryTotalsByContinent.forEach(System.out::println);
        }
        response.join();
        return countryTotalsByContinent;
    }

}

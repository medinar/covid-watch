package com.medinar.covidwatch.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.medinar.covidwatch.domain.ContinentalTotal;
import static com.medinar.covidwatch.service.AbstractService.HTTP_CLIENT;
import com.medinar.covidwatch.utility.JSONUtils;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Rommel Medina
 */
@Service
public class ContinentalServiceImpl extends AbstractService implements ContinentalService {

    @Override
    public ContinentalTotal getTotal(
            String continent
    ) throws InterruptedException, ExecutionException, IOException {

        HttpRequest request = HttpRequest
                .newBuilder(URI.create(CONTINENTAL_TOTAL_URL))
                .header("Content-Type", "application/json")
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
            System.out.println("Continental Total Not Avaialble");
        } else {
            continentalTotal = continentalTotals.stream()
                    .filter(ct -> ct.getContinent().equalsIgnoreCase(continent))
                    .findFirst();
        }
        response.join();

        return continentalTotal.get();
    }

    @Override
    public List<ContinentalTotal> getTotals(
            boolean yesterday,
            boolean twoDaysAgo,
            String sortBy,
            boolean allowNull
    ) throws InterruptedException, ExecutionException, IOException {

        StringBuilder sbContinentalTotalUrl = new StringBuilder(100);
        sbContinentalTotalUrl.append(GLOBAL_TOTAL_URL)
                .append("?yesterday=").append(yesterday)
                .append("&twoDaysAgo=").append(twoDaysAgo);
        if (!sortBy.isBlank()) {
            sbContinentalTotalUrl.append("&sort=").append(sortBy);
        }
        sbContinentalTotalUrl.append("&allowNull=").append(allowNull);

        HttpRequest request = HttpRequest
                .newBuilder(URI.create(CONTINENTAL_TOTAL_URL))
                .header("Content-Type", "application/json")
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

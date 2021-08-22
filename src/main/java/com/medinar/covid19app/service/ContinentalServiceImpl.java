package com.medinar.covid19app.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.medinar.covid19app.domain.ContinentalTotal;
import static com.medinar.covid19app.service.AbstractService.HTTP_CLIENT;
import static com.medinar.covid19app.service.Covid19ServiceImpl.CONTINENTAL_TOTAL_URL;
import com.medinar.covid19app.utility.JSONUtils;
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
    public ContinentalTotal getTotalByContinent(
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
                .convertFromJsonToList(response.get().body(), new TypeReference<List<ContinentalTotal>>() {
                });

        Optional<ContinentalTotal> continentalTotal = Optional.empty();
        if (response.get().statusCode() == 500) {
            System.out.println("Continent Totals Not Avaialble");
        } else {
            continentalTotal = continentalTotals.stream()
                    .filter(ct -> ct.getContinent().equalsIgnoreCase(continent))
                    .findFirst();
        }
        response.join();

        return continentalTotal.get();
    }

}

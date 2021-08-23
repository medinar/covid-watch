package com.medinar.covidwatch.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.medinar.covidwatch.client.ContinentalClient;
import com.medinar.covidwatch.config.CovidApiConfig;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Rommel Medina
 */
@Service
public class ContinentalServiceImpl extends AbstractService implements ContinentalService {

    @Autowired
    ContinentalClient client;

    @Override
    public Optional<ContinentalTotal> getTotal(
            String continent,
            boolean yesterday,
            boolean twoDaysAgo,
            boolean strict,
            boolean allowNull
    ) throws InterruptedException, ExecutionException, IOException {

        return client.getContinentalTotal(
                continent,
                yesterday,
                twoDaysAgo,
                strict,
                allowNull
        );
    }

    @Override
    public List<ContinentalTotal> getTotals(
            boolean yesterday,
            boolean twoDaysAgo,
            boolean strict,
            boolean allowNull
    ) throws InterruptedException, ExecutionException, IOException {
        return client.getContinentalTotals(
                yesterday,
                twoDaysAgo,
                strict,
                allowNull
        );
    }

}

package com.medinar.covidwatch.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.medinar.covidwatch.client.InternationalClient;
import com.medinar.covidwatch.config.CovidApiConfig;
import com.medinar.covidwatch.domain.InternationalTotal;
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
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.stereotype.Service;

/**
 *
 * @author Rommel Medina
 */
@Service
public class InternationalServiceImpl implements InternationalService {

    @Autowired
    CovidApiConfig config;

    @Autowired
    InternationalClient client;

    @Override
    public List<InternationalTotal> getTotals(
            boolean yesterday,
            boolean twoDaysAgo,
            String sortBy,
            boolean allowNull
    ) throws InterruptedException, ExecutionException, IOException {

        return client.getInternationalTotals(
                yesterday, 
                twoDaysAgo, 
                sortBy, 
                allowNull
        );
    }

    @Override
    public InternationalTotal getTotal(
            String country,
            boolean yesterday,
            boolean twoDaysAgo,
            boolean strict,
            boolean allowNull
    ) throws InterruptedException, ExecutionException, IOException {

        return client.getInternationalTotal(
                country,
                yesterday,
                twoDaysAgo,
                strict,
                allowNull
        );

    }

    @Override
    public List<InternationalTotal> getTotalsbyContinent(
            String continent,
            boolean yesterday,
            boolean twoDaysAgo,
            boolean strict,
            boolean allowNull
    ) throws InterruptedException, ExecutionException, IOException {
        
        return client.getInternationalTotalsbyContinent(
                continent, 
                yesterday, 
                twoDaysAgo, 
                strict, 
                allowNull
        );
    }

}

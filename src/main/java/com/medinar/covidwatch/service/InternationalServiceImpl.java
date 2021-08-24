package com.medinar.covidwatch.service;

import com.medinar.covidwatch.client.InternationalClient;
import com.medinar.covidwatch.config.CovidApiConfig;
import com.medinar.covidwatch.domain.InternationalTotal;
import com.medinar.covidwatch.exception.InternationalCasesNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
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
    ) throws InterruptedException, 
            ExecutionException, 
            IOException, 
            InternationalCasesNotFoundException {

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

package com.medinar.covidwatch.service;

import com.medinar.covidwatch.client.ContinentalClient;
import com.medinar.covidwatch.domain.ContinentalTotal;
import com.medinar.covidwatch.exception.ContinentNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Rommel Medina
 */
@Service
public class ContinentalServiceImpl implements ContinentalService {

    @Autowired
    ContinentalClient client;

    @Override
    public Optional<ContinentalTotal> getTotal(
            String continent,
            boolean yesterday,
            boolean twoDaysAgo,
            boolean strict,
            boolean allowNull
    ) throws InterruptedException, ExecutionException, IOException, ContinentNotFoundException {

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

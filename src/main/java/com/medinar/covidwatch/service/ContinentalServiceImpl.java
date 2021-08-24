package com.medinar.covidwatch.service;

import com.medinar.covidwatch.client.ContinentalClient;
import com.medinar.covidwatch.domain.ContinentalTotal;
import com.medinar.covidwatch.enums.Continent;
import com.medinar.covidwatch.exception.ContinentalCasesNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Rommel Medina
 */
@Slf4j
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
    ) throws InterruptedException, 
            ExecutionException, 
            IOException, 
            ContinentalCasesNotFoundException {

        Optional<Continent> oContinent = Arrays.asList(Continent.values()).stream()
                .filter(c -> c.getValue().equalsIgnoreCase(continent))
                .findFirst();

        if (oContinent.isEmpty()) {
            throw new ContinentalCasesNotFoundException(
                    "Continent not found: " + continent
            );
        }

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
    ) throws InterruptedException,
            ExecutionException,
            IOException,
            ContinentalCasesNotFoundException {

        return client.getContinentalTotals(
                yesterday,
                twoDaysAgo,
                strict,
                allowNull
        );
    }

}

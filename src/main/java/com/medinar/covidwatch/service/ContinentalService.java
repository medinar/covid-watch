package com.medinar.covidwatch.service;

import com.medinar.covidwatch.domain.ContinentalTotal;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author Rommel Medina
 */
public interface ContinentalService {

    public abstract ContinentalTotal getTotalByContinent(
            String continent
    ) throws InterruptedException, ExecutionException, IOException;

}

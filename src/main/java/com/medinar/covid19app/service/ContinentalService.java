package com.medinar.covid19app.service;

import com.medinar.covid19app.domain.ContinentalTotal;
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

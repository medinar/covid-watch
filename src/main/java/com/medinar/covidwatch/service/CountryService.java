package com.medinar.covidwatch.service;

import com.medinar.covidwatch.domain.CountryTotal;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author Rommel Medina
 */
public interface CountryService {

    public abstract List<CountryTotal> getTotals() 
            throws InterruptedException, ExecutionException, IOException;

    public abstract CountryTotal getTotal(String country) 
            throws InterruptedException, ExecutionException, IOException;

}

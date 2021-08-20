package com.medinar.covid19app.service;

import com.medinar.covid19app.domain.ContinentalTotal;
import com.medinar.covid19app.domain.CountryTotal;
import com.medinar.covid19app.domain.WorldTotal;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author Rommel Medina
 */
public interface Covid19Service {

    public abstract WorldTotal getWorldTotal(
            boolean yesterday,
            boolean twoDaysAgo,
            boolean allowNull
    ) throws InterruptedException, ExecutionException, IOException;

    /**
     *
     * @param yesterday Queries data reported a day ago
     * @param twoDaysAgo Queries data reported two days ago
     * @param sortBy Provided a key (e.g. cases, todayCases, deaths, recovered,
     * active), results will be sorted from greatest to least
     * @param allowNull By default, if a value is missing, it is returned as 0.
     * This allows nulls to be returned
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws IOException
     */
    public abstract List<ContinentalTotal> getContinentalTotals(
            boolean yesterday,
            boolean twoDaysAgo,
            String sortBy,
            boolean allowNull
    ) throws InterruptedException, ExecutionException, IOException;

    /**
     *
     * @param yesterday Queries data reported a day ago
     * @param twoDaysAgo Queries data reported two days ago
     * @param sortBy Provided a key (e.g. cases, todayCases, deaths, recovered,
     * active), results will be sorted from greatest to least
     * @param allowNull By default, if a value is missing, it is returned as 0.
     * This allows nulls to be returned
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws IOException
     */
    public abstract List<CountryTotal> getNationalTotals(
            boolean yesterday,
            boolean twoDaysAgo,
            String sortBy,
            boolean allowNull
    ) throws InterruptedException, ExecutionException, IOException;

    /* 
     * @param yesterday Queries data reported a day ago
     * @param twoDaysAgo Queries data reported two days ago
     * @param sortBy Provided a key (e.g. cases, todayCases, deaths, recovered, active), results will be sorted from greatest to least
     * @param allowNull By default, if a value is missing, it is returned as 0. This allows nulls to be returned
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws IOException 
     */
    public abstract List<CountryTotal> getNationalTotalsbyContinent(
            String continent,
            boolean yesterday,
            boolean twoDaysAgo,
            String sortBy,
            boolean allowNull
    ) throws InterruptedException, ExecutionException, IOException;

    public abstract CountryTotal getCountryTotal(
            String country,
            boolean yesterday,
            boolean twoDaysAgo,
            String sortBy,
            boolean allowNull
    ) throws InterruptedException, ExecutionException, IOException;

}

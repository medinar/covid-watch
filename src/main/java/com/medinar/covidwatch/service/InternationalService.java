package com.medinar.covidwatch.service;

import com.medinar.covidwatch.domain.InternationalTotal;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author Rommel Medina
 */
public interface InternationalService {

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
    public abstract List<InternationalTotal> getTotals(
            boolean yesterday,
            boolean twoDaysAgo,
            String sortBy,
            boolean allowNull
    ) throws InterruptedException, ExecutionException, IOException;

    public abstract InternationalTotal getTotal(
            String country,
            boolean yesterday,
            boolean twoDaysAgo,
            String sortBy,
            boolean allowNull
    )  throws InterruptedException, ExecutionException, IOException;
    
    /* 
     * @param continent Name of the continent
     * @param yesterday Queries data reported a day ago
     * @param twoDaysAgo Queries data reported two days ago
     * @param sortBy Provided a key (e.g. cases, todayCases, deaths, recovered, active), results will be sorted from greatest to least
     * @param allowNull By default, if a value is missing, it is returned as 0. This allows nulls to be returned
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws IOException 
     */
    public abstract List<InternationalTotal> getTotalsbyContinent(
            String continent,
            boolean yesterday,
            boolean twoDaysAgo,
            String sortBy,
            boolean allowNull
    ) throws InterruptedException, ExecutionException, IOException;

}
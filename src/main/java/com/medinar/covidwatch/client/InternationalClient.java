package com.medinar.covidwatch.client;

import com.medinar.covidwatch.domain.InternationalTotal;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author Rommel Medina
 */
public interface InternationalClient {

    public abstract InternationalTotal getInternationalTotal(
            String country,
            boolean yesterday,
            boolean twoDaysAgo,
            boolean strict,
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
    public abstract List<InternationalTotal> getInternationalTotals(
            boolean yesterday,
            boolean twoDaysAgo,
            String sortBy,
            boolean allowNull
    ) throws InterruptedException, ExecutionException, IOException;    
    
    /* 
     * Retrieves the list of totals in different countries for the given continent
     *
     * @param continent Name of the continent
     * @param yesterday Queries data reported a day ago
     * @param twoDaysAgo Queries data reported two days ago
     * @param strict Setting to false gives you the ability to fuzzy search continents (i.e. Oman vs. rOMANia). Default is true.
     * @param allowNull By default, if a value is missing, it is returned as 0. This allows nulls to be returned
     * @return List of totals in different countries for the given continent
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws IOException 
     */
    public abstract List<InternationalTotal> getInternationalTotalsbyContinent(
            String continent,
            boolean yesterday,
            boolean twoDaysAgo,
            boolean strict,
            boolean allowNull
    ) throws InterruptedException, ExecutionException, IOException;
}
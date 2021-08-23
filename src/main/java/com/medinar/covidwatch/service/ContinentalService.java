package com.medinar.covidwatch.service;

import com.medinar.covidwatch.domain.ContinentalTotal;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author Rommel Medina
 */
public interface ContinentalService {

    /**
     * Retrieves the total for a specific continent
     *
     * @param continent Continent name
     * @param yesterday Queries data reported a day ago
     * @param twoDaysAgo Queries data reported two days ago
     * @param strict Setting to false gives you the ability to fuzzy search
     * continents (i.e. Oman vs. rOMANia). Default is true.
     * @param allowNull By default, if a value is missing, it is returned as 0.
     * This allows nulls to be returned
     * @return Total for a specific continent
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws IOException
     */
    public abstract ContinentalTotal getTotal(
            String continent,
            boolean yesterday,
            boolean twoDaysAgo,
            boolean strict,
            boolean allowNull
    ) throws InterruptedException, ExecutionException, IOException;

    /**
     * Retrieves the list of totals of all continents
     *
     * @param yesterday Queries data reported a day ago
     * @param twoDaysAgo Queries data reported two days ago
     * @param strict Setting to false gives you the ability to fuzzy search
     * continents (i.e. Oman vs. rOMANia). Default is true.
     * @param allowNull By default, if a value is missing, it is returned as 0.
     * This allows nulls to be returned
     * @return List of totals of all continents
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws IOException
     */
    public abstract List<ContinentalTotal> getTotals(
            boolean yesterday,
            boolean twoDaysAgo,
            boolean strict,
            boolean allowNull
    ) throws InterruptedException, ExecutionException, IOException;

}

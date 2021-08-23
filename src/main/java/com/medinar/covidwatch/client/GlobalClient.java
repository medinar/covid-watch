package com.medinar.covidwatch.client;

import com.medinar.covidwatch.domain.GlobalTotal;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author Rommel Medina
 */
public interface GlobalClient {

    /**
     * Retrieves the total cases worldwide
     *
     * @param yesterday Queries data reported a day ago
     * @param twoDaysAgo Queries data reported two days ago
     * @param allowNull By default, if a value is missing, it is returned as 0.
     * This allows nulls to be returned
     * @return Total cases worldwide
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws IOException
     */
    public abstract GlobalTotal getGlobalTotal(
            boolean yesterday,
            boolean twoDaysAgo,
            boolean allowNull
    ) throws InterruptedException, ExecutionException, IOException;

}
package com.medinar.covid19app.service;

import com.medinar.covid19app.domain.WorldTotal;
import java.io.IOException;
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
    
}

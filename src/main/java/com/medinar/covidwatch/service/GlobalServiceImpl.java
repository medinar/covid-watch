package com.medinar.covidwatch.service;

import com.medinar.covidwatch.client.GlobalClient;
import com.medinar.covidwatch.domain.GlobalTotal;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Rommel Medina
 */
@Service
public class GlobalServiceImpl implements GlobalService {
    
    @Autowired
    GlobalClient client;
    
    @Override
    public GlobalTotal getTotal(
            boolean yesterday,
            boolean twoDaysAgo,
            boolean allowNull
    ) throws InterruptedException, ExecutionException, IOException {
        return client.getGlobalTotal(yesterday, twoDaysAgo, allowNull);
    }

}

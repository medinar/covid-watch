package com.medinar.covid19app.service;

import com.medinar.covid19app.domain.CountryTotal;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author Rommel Medina
 */
public class CountryServiceImpl implements CountryService {

    @Override
    public List<CountryTotal> getTotals() throws InterruptedException, ExecutionException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CountryTotal getTotal(String country) throws InterruptedException, ExecutionException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

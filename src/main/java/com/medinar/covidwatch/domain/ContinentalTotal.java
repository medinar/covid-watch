package com.medinar.covidwatch.domain;

import java.util.List;
import lombok.Data;

/**
 *
 * @author Rommel Medina
 */
@Data
public class ContinentalTotal {
    public long updated;
    public int cases;
    public int todayCases;
    public int deaths;
    public int todayDeaths;
    public int recovered;
    public int todayRecovered;
    public int active;
    public int critical;
    public double casesPerOneMillion;
    public double deathsPerOneMillion;
    public int tests;
    public double testsPerOneMillion;
    public long population;
    public String continent;
    public double activePerOneMillion;
    public double recoveredPerOneMillion;
    public double criticalPerOneMillion;
    public ContinentalInfo continentInfo;
    public List<String> countries;
}
package com.medinar.covidwatch.domain;

import java.util.List;
import lombok.Data;

/**
 *
 * @author Rommel Medina
 */
@Data
public class ContinentalTotal {

    long updated;
    int cases;
    int todayCases;
    int deaths;
    int todayDeaths;
    int recovered;
    int todayRecovered;
    int active;
    int critical;
    double casesPerOneMillion;
    double deathsPerOneMillion;
    int tests;
    double testsPerOneMillion;
    long population;
    String continent;
    double activePerOneMillion;
    double recoveredPerOneMillion;
    double criticalPerOneMillion;
    ContinentalInfo continentInfo;
    List<String> countries;
    
}

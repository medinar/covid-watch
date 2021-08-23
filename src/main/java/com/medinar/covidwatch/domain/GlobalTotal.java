package com.medinar.covidwatch.domain;

import lombok.Data;

/**
 *
 * @author Rommel Medina
 */
@Data
public class GlobalTotal {

    long updated;
    int cases;
    int todayCases;
    int deaths;
    int todayDeaths;
    int recovered;
    int todayRecovered;
    int active;
    int critical;
    int casesPerOneMillion;
    double deathsPerOneMillion;
    long tests;
    double testsPerOneMillion;
    long population;
    int oneCasePerPeople;
    int oneDeathPerPeople;
    int oneTestPerPeople;
    double activePerOneMillion;
    double recoveredPerOneMillion;
    double criticalPerOneMillion;
    int affectedCountries;

}

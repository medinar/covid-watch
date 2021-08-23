package com.medinar.covidwatch.domain;

import lombok.Data;

/**
 *
 * @author Rommel Medina
 */
@Data
public class InternationalTotal {

    long updated;
    String country;
    CountryInfo countryInfo;
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
    int tests;
    int testsPerOneMillion;
    int population;
    String continent;
    int oneCasePerPeople;
    int oneDeathPerPeople;
    int oneTestPerPeople;
    double activePerOneMillion;
    double recoveredPerOneMillion;
    double criticalPerOneMillion;
}

package com.medinar.covid19app.domain;

import lombok.Data;

/**
 *
 * @author Rommel Medina
 */
@Data
public class CountryTotal {

    public Object updated;
    public String country;
    public CountryInfo countryInfo;
    public int cases;
    public int todayCases;
    public int deaths;
    public int todayDeaths;
    public int recovered;
    public int todayRecovered;
    public int active;
    public int critical;
    public int casesPerOneMillion;
    public double deathsPerOneMillion;
    public int tests;
    public int testsPerOneMillion;
    public int population;
    public String continent;
    public int oneCasePerPeople;
    public int oneDeathPerPeople;
    public int oneTestPerPeople;
    public double activePerOneMillion;
    public double recoveredPerOneMillion;
    public double criticalPerOneMillion;
}

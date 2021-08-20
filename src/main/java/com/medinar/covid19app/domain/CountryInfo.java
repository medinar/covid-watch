package com.medinar.covid19app.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Rommel Medina
 */
public class CountryInfo {
    public int _id;
    public String iso2;
    public String iso3;
    @JsonProperty("lat")
    public double latitude;
    @JsonProperty("long")
    public double longitude;
    public String flag;
}

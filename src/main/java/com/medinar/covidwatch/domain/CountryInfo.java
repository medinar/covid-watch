package com.medinar.covidwatch.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 *
 * @author Rommel Medina
 */
@Data
public class CountryInfo {

    int id;
    String iso2;
    String iso3;
    @JsonProperty("lat")
    double latitude;
    @JsonProperty("long")
    double longitude;
    String flag;
}

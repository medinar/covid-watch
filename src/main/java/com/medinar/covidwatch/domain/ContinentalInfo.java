package com.medinar.covidwatch.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 *
 * @author Rommel Medina
 */
@Data
public class ContinentalInfo {
    @JsonProperty("lat")
    public double latitude;
    @JsonProperty("long")
    public double longitude;
}

package com.medinar.covidwatch.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Rommel Medina
 */
@Data
@ConfigurationProperties(prefix = "covidapi")
@Configuration
public class CovidApiConfig {

    private String baseUrl;
    private String globalResource;
    private String continentalResource;
    private String internationalResource;
    private String env;

}

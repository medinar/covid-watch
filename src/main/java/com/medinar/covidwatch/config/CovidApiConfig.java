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

    String baseUrl;
    String globalResource;
    String continentalResource;
    String internationalResource;
    String env;

}

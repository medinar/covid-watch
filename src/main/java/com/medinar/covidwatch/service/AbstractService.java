package com.medinar.covidwatch.service;

import java.net.http.HttpClient;

/**
 *
 * @author Rommel Medina
 */
public abstract class AbstractService {

    /**
     * Used to send requests and receive responses. Provides Synchronous and
     * Asynchronous request mechanisms. HttpClient instance is immutable, once
     * created you can send multiple requests with the same. To send requests,
     * first you need to create HttpClient.
     *
     * If HTTP/2 is not supported by the server, processes the request using
     * HTTP/1.1 You can use executor() for asynchronous tasks.
     */
    protected static final HttpClient HTTP_CLIENT = HttpClient
            .newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();
    
    protected static final String BASE_URL = "https://disease.sh/v3/covid-19";
    protected static final String GLOBAL_TOTAL_URL = BASE_URL + "/all";
    protected static final String CONTINENTAL_TOTAL_URL = BASE_URL + "/continents";
    protected static final String COUNTRIES_TOTAL_URL = BASE_URL + "/countries";
    protected static final String COUNTRY_TOTAL_URL = BASE_URL + "/countries";

}

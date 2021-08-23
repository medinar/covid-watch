package com.medinar.covidwatch.client;

import java.net.http.HttpClient;

/**
 *
 * @author Rommel Medina
 */
public abstract class AbstractClient {
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

}

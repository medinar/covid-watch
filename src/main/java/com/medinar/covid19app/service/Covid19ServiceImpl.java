package com.medinar.covid19app.service;

import com.medinar.covid19app.domain.WorldTotal;
import com.medinar.covid19app.utility.JSONUtils;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Rommel Medina
 */
@Service
public class Covid19ServiceImpl implements Covid19Service {

    public static final String WORLD_TOTAL_URL = "https://disease.sh/v3/covid-19/all?yesterday=true&twoDaysAgo=true&allowNull=false";

    @Override
    public WorldTotal getWorldTotal(
            boolean yesterday,
            boolean twoDaysAgo,
            boolean allowNull
    ) throws InterruptedException, ExecutionException, IOException {

        WorldTotal worldTotal = null;

        // https://dzone.com/articles/java-11-http-client-api-to-consume-restful-web-ser-1
        /**
         * Used to send requests and receive responses. Provides Synchronous and
         * Asynchronous request mechanisms. HttpClient instance is immutable,
         * once created you can send multiple requests with the same. To send
         * requests, first you need to create HttpClient.
         *
         * If HTTP/2 is not supported by the server, processes the request using
         * HTTP/1.1 You can use executor() for asynchronous tasks.
         */
        HttpClient client = HttpClient.newBuilder().version(Version.HTTP_2).build();

        /**
         * Create HttpRequest instance and set the URI, request method
         * optionally specify the body and headers. HttpRequest instance is
         * immutable and can be sent multiple times. Client supports all HTTP
         * methods. Methods available to make different requests are GET(),
         * POST(), PUT(), DELETE(), method().
         */
        HttpRequest request = HttpRequest
                .newBuilder(URI.create(WORLD_TOTAL_URL))
                .header("Content-Type", "application/json")
                //                .POST(HttpRequest.BodyPublishers.ofString(inputJson))
                .GET()
                .build();

        CompletableFuture<HttpResponse<String>> response = client
                .sendAsync(request, HttpResponse.BodyHandlers.ofString());

        response.thenAccept(res -> System.out.println(res));

        if (response.get().statusCode() == 500) {
            System.out.println("Product Not Avaialble");
        } else {
            worldTotal = JSONUtils.covertFromJsonToObject(response.get().body(), WorldTotal.class);
            System.out.println(worldTotal);
        }
        response.join();
        return worldTotal;
    }

}

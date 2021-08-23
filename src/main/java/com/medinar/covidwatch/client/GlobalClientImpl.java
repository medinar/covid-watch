package com.medinar.covidwatch.client;

import com.medinar.covidwatch.config.CovidApiConfig;
import static com.medinar.covidwatch.constant.Constants.CONTENT_TYPE;
import com.medinar.covidwatch.domain.GlobalTotal;
import com.medinar.covidwatch.utility.JSONUtils;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.stereotype.Service;

/**
 *
 * @author Rommel Medina
 */
@Service
public class GlobalClientImpl extends AbstractClient implements GlobalClient {

    @Autowired
    CovidApiConfig config;

    @Override
    public GlobalTotal getGlobalTotal(
            boolean yesterday,
            boolean twoDaysAgo,
            boolean allowNull
    ) throws InterruptedException, ExecutionException, IOException {

        System.out.println("CovidApiConfig ::: " + config.toString());

        StringBuilder sbGlobalTotalUrl = new StringBuilder(100);
        sbGlobalTotalUrl.append(config.getBaseUrl())
                .append(config.getGlobalResource())
                .append("?yesterday=").append(yesterday)
                .append("&twoDaysAgo=").append(twoDaysAgo)
                .append("&allowNull=").append(allowNull);

        /**
         * Create HttpRequest instance and set the URI, request method
         * optionally specify the body and headers. HttpRequest instance is
         * immutable and can be sent multiple times. Client supports all HTTP
         * methods. Methods available to make different requests are GET(),
         * POST(), PUT(), DELETE(), method().
         */
        HttpRequest request = HttpRequest
                .newBuilder(URI.create(sbGlobalTotalUrl.toString()))
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .GET()
                .build();

        CompletableFuture<HttpResponse<String>> response = HTTP_CLIENT
                .sendAsync(request, HttpResponse.BodyHandlers.ofString());

        response.thenAccept(res -> System.out.println(res));

        GlobalTotal globalTotal = null;
        if (response.get().statusCode() == 500) {
            System.out.println("Global totals not avaialble");
        } else {
            globalTotal = JSONUtils.covertFromJsonToObject(
                    response.get().body(),
                    GlobalTotal.class
            );
            System.out.println(globalTotal);
        }
        response.join();
        return globalTotal;
    }

}

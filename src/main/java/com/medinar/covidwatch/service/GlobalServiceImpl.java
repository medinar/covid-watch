package com.medinar.covidwatch.service;

import com.medinar.covidwatch.domain.GlobalTotal;
import static com.medinar.covidwatch.service.AbstractService.HTTP_CLIENT;
import com.medinar.covidwatch.utility.JSONUtils;
import java.io.IOException;
import java.net.URI;
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
public class GlobalServiceImpl extends AbstractService implements GlobalService {

    @Override
    public GlobalTotal getTotal(
            boolean yesterday,
            boolean twoDaysAgo,
            boolean allowNull
    ) throws InterruptedException, ExecutionException, IOException {

        StringBuilder sbGlobalTotalUrl = new StringBuilder(100);
        sbGlobalTotalUrl.append(GLOBAL_TOTAL_URL)
                .append("?yesterday=").append(yesterday)
                .append("&twoDaysAgo=").append(twoDaysAgo)
                .append("&allowNull=").append(allowNull);

        // https://dzone.com/articles/java-11-http-client-api-to-consume-restful-web-ser-1
        /**
         * Create HttpRequest instance and set the URI, request method
         * optionally specify the body and headers. HttpRequest instance is
         * immutable and can be sent multiple times. Client supports all HTTP
         * methods. Methods available to make different requests are GET(),
         * POST(), PUT(), DELETE(), method().
         */
        HttpRequest request = HttpRequest
                .newBuilder(URI.create(sbGlobalTotalUrl.toString()))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        CompletableFuture<HttpResponse<String>> response = HTTP_CLIENT
                .sendAsync(request, HttpResponse.BodyHandlers.ofString());

        response.thenAccept(res -> System.out.println(res));

        GlobalTotal globalTotal = null;
        if (response.get().statusCode() == 500) {
            System.out.println("Global totals not avaialble");
        } else {
            globalTotal = JSONUtils.covertFromJsonToObject(response.get().body(), GlobalTotal.class);
            System.out.println(globalTotal);
        }
        response.join();
        return globalTotal;
    }

}

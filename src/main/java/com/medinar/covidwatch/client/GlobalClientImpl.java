package com.medinar.covidwatch.client;

import com.medinar.covidwatch.config.CovidApiConfig;
import static com.medinar.covidwatch.constant.Constants.ALLWNULL_REQ_PARAM;
import static com.medinar.covidwatch.constant.Constants.CONTENT_TYPE;
import static com.medinar.covidwatch.constant.Constants.INTERNAL_SERVER_CODE;
import static com.medinar.covidwatch.constant.Constants.INTERNAL_SERVER_ERROR;
import static com.medinar.covidwatch.constant.Constants.TWODAYSAGO_REQ_PARAM;
import static com.medinar.covidwatch.constant.Constants.YESTERDAY_REQ_PARAM;
import com.medinar.covidwatch.domain.GlobalTotal;
import com.medinar.covidwatch.exception.GlobalCasesNotFoundException;
import com.medinar.covidwatch.utility.JSONUtils;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.stereotype.Service;

/**
 *
 * @author Rommel Medina
 */
@Slf4j
@Service
public class GlobalClientImpl extends AbstractClient implements GlobalClient {

    @Autowired
    CovidApiConfig config;

    @Override
    public GlobalTotal getGlobalTotal(
            boolean yesterday,
            boolean twoDaysAgo,
            boolean allowNull
    ) throws InterruptedException, ExecutionException, IOException, GlobalCasesNotFoundException {

        if (log.isDebugEnabled()) {
            log.debug("CovidApiConfig ::: {}", config.toString());
        }

        StringBuilder sbGlobalTotalUrl = new StringBuilder(100);
        sbGlobalTotalUrl.append(config.getBaseUrl())
                .append(config.getGlobalResource())
                .append(YESTERDAY_REQ_PARAM).append(yesterday)
                .append(TWODAYSAGO_REQ_PARAM).append(twoDaysAgo)
                .append(ALLWNULL_REQ_PARAM).append(allowNull);

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

        response.thenAccept(res -> log.info(res.toString()));

        GlobalTotal globalTotal = null;
        if (response.get().statusCode() == INTERNAL_SERVER_CODE) {
            log.error(INTERNAL_SERVER_ERROR);
            throw new GlobalCasesNotFoundException("Global totals not available");
        }
        else {
            globalTotal = JSONUtils.covertFromJsonToObject(
                    response.get().body(),
                    GlobalTotal.class
            );
            log.info(globalTotal.toString());
        }
        response.join();
        return globalTotal;
    }

}

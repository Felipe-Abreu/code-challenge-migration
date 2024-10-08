package com.example.dummyjson.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Configuration
public class WebClientConfig {

    @Value("${app.base_url_dummyjson}")
    private String baseUrl;

    public WebClient consultDummyJson() {
        return WebClient.builder()
                .baseUrl(baseUrl)
                .filter(ExchangeFilterFunction.ofResponseProcessor(this::exchangeFilterResponseProcessor))
                .build();
    }

    private Mono<ClientResponse> exchangeFilterResponseProcessor(ClientResponse response) {
        HttpStatusCode status = response.statusCode();
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            return response.bodyToMono(String.class).flatMap(body -> Mono.error(new RuntimeException(body)));
        }
        return Mono.just(response);
    }

}

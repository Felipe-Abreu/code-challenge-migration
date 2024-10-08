package com.example.dummyjson.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class WebClientConfigTest {

    @InjectMocks
    private WebClientConfig config;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void consultDummyJson_ShouldReturnWebClient() {
        WebClient webClient = config.consultDummyJson();

        assertNotNull(webClient);
    }
}

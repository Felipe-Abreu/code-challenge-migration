package com.example.dummyjson.service;

import com.example.dummyjson.config.WebClientConfig;
import com.example.dummyjson.dto.Product;
import com.example.dummyjson.dto.ProductResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

class ProductWebClientServiceTest {

    @InjectMocks
    private ProductWebClientService service;

    @Mock
    private WebClientConfig webClientConfig;

    @Mock
    private WebClient webClient;

    @Mock
    private RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    private RequestHeadersSpec requestHeadersSpec;

    @Mock
    private ResponseSpec responseSpec;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllProductsByWebClient_Success() {
        List<Product> mockProducts = List.of(new Product(1L, "Product 1", "", 100.0));
        ProductResponse mockResponse = new ProductResponse(mockProducts);

        when(webClientConfig.consultDummyJson()).thenReturn(webClient);
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(ProductResponse.class)).thenReturn(Mono.just(mockResponse));

        ResponseEntity<List<Product>> response = service.getAllProductsByWebClient();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockProducts, response.getBody());
    }


    @Test
    void getByWebClientProductById_NotFound() {
        when(webClientConfig.consultDummyJson()).thenReturn(webClient);
        when(requestHeadersUriSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(Product.class)).thenThrow(new RuntimeException("Not Found"));

        assertThrows(RuntimeException.class, () -> service.getByWebClientProductById(1L));
    }
}

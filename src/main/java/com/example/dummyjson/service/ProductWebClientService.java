package com.example.dummyjson.service;

import com.example.dummyjson.config.WebClientConfig;
import com.example.dummyjson.dto.Product;
import com.example.dummyjson.dto.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductWebClientService {

    private final WebClientConfig webClientConfig;

    public ResponseEntity<List<Product>> getAllProductsByWebClient() {

        ProductResponse webClientProducts = webClientConfig.consultDummyJson()
                .get()
                .retrieve()
                .onStatus(HttpStatus.BAD_REQUEST::equals, response -> response.bodyToMono(String.class).map(RuntimeException::new))
                .bodyToMono(ProductResponse.class)
                .block();

        if (webClientProducts == null || webClientProducts.products() == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(webClientProducts.products());
    }

    public Product getByWebClientProductById(Long id) {
        return webClientConfig.consultDummyJson()
                .get()
                .uri(uriBuilder -> uriBuilder.path("/{id}").build(id))
                .retrieve()
                .onStatus(HttpStatus.BAD_REQUEST::equals, response -> response.bodyToMono(String.class).map(RuntimeException::new))
                .bodyToMono(Product.class)
                .block();
    }
}

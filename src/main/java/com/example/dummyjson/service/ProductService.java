package com.example.dummyjson.service;

import com.example.dummyjson.dto.Product;
import com.example.dummyjson.dto.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final RestTemplate restTemplate;
    @Value("${app.base_url_dummyjson}")
    private String baseUrl;

    public ResponseEntity<List<Product>> getAllProducts() {
        ProductResponse response = restTemplate.getForObject(baseUrl, ProductResponse.class);
        return response != null
                ? ResponseEntity.ok(response.products())
                : ResponseEntity.ok(Collections.emptyList());
    }

    public Product getProductById(Long id) {
        String url = baseUrl + "/" + id;
        return restTemplate.getForObject(url, Product.class);
    }

}

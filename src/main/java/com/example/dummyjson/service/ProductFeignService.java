package com.example.dummyjson.service;

import com.example.dummyjson.config.FeignProduct;
import com.example.dummyjson.dto.Product;
import com.example.dummyjson.dto.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductFeignService {

    private final FeignProduct feignProduct;

    public ResponseEntity<List<Product>> getAllProductsByWebClient() {
        ProductResponse productsResponse = feignProduct.getAllProducts();

        if (productsResponse == null || productsResponse.products() == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(productsResponse.products());
    }

    public Product getByWebClientProductById(Long id) {
        return feignProduct.getProductById(id);
    }

}

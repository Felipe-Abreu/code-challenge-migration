package com.example.dummyjson.service;

import com.example.dummyjson.config.FeignProduct;
import com.example.dummyjson.dto.Product;
import com.example.dummyjson.dto.ProductResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ProductFeignServiceTest {

    private final FeignProduct feignProduct = Mockito.mock(FeignProduct.class);
    private final ProductFeignService productFeignService = new ProductFeignService(feignProduct);

    @Test
    void testGetAllProductsByWebClient_NonEmptyResponse() {
        Product product = new Product();
        ProductResponse response = new ProductResponse(Collections.singletonList(product));
        when(feignProduct.getAllProducts()).thenReturn(response);

        ResponseEntity<List<Product>> result = productFeignService.getAllProductsByWebClient();

        assertEquals(200, result.getStatusCodeValue());
        assertEquals(1, Objects.requireNonNull(result.getBody()).size());
        assertEquals(product, result.getBody().getFirst());
    }

    @Test
    void testGetAllProductsByWebClient_EmptyResponse() {
        when(feignProduct.getAllProducts()).thenReturn(new ProductResponse(null));

        ResponseEntity<List<Product>> result = productFeignService.getAllProductsByWebClient();

        assertEquals(204, result.getStatusCodeValue());
    }

    @Test
    void testGetAllProductsByWebClient_NullResponse() {
        when(feignProduct.getAllProducts()).thenReturn(null);

        ResponseEntity<List<Product>> result = productFeignService.getAllProductsByWebClient();

        assertEquals(204, result.getStatusCodeValue());
    }

    @Test
    void testGetByWebClientProductById() {
        Long productId = 1L;
        Product product = new Product();
        when(feignProduct.getProductById(productId)).thenReturn(product);

        Product result = productFeignService.getByWebClientProductById(productId);

        assertEquals(product, result);
    }
}

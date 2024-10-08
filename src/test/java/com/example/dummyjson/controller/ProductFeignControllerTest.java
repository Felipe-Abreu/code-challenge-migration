package com.example.dummyjson.controller;

import com.example.dummyjson.dto.Product;
import com.example.dummyjson.service.ProductFeignService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ProductFeignControllerTest {

    private final ProductFeignService service = Mockito.mock(ProductFeignService.class);
    private final ProductFeignController controller = new ProductFeignController(service);

    @Test
    void testGetAllProducts() {
        Product product = new Product();
        when(service.getAllProductsByWebClient()).thenReturn(ResponseEntity.ok(Collections.singletonList(product)));

        ResponseEntity<List<Product>> response = controller.getAllProducts();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, Objects.requireNonNull(response.getBody()).size());
        assertEquals(product, response.getBody().getFirst());
    }

    @Test
    void testGetProductById() {
        Long productId = 1L;
        Product product = new Product();
        when(service.getByWebClientProductById(productId)).thenReturn(product);

        Product result = controller.getProductById(productId);

        assertEquals(product, result);
    }
}

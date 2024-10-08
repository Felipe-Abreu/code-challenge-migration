package com.example.dummyjson.controller;

import com.example.dummyjson.dto.Product;
import com.example.dummyjson.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @Test
    void testGetAllProducts() {
        Product product1 = new Product(1L, "Product 1", "", 0.0);

        Product product2 = new Product(2L, "Product 2", "", 0.0);

        List<Product> products = Arrays.asList(product1, product2);

        when(productService.getAllProducts()).thenReturn(ResponseEntity.ok(products));

        ResponseEntity<List<Product>> response = productController.getAllProducts();

        List<Product> result = response.getBody();
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Product 1", result.get(0).getTitle());
        assertEquals("Product 2", result.get(1).getTitle());
    }

    @Test
    void testGetProductById() {
        Product product = new Product(1L, "Product 1", "", 0.0);

        when(productService.getProductById(1L)).thenReturn(product);

        Product result = productController.getProductById(1L);
        assertEquals("Product 1", result.getTitle());
    }
}

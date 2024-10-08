package com.example.dummyjson.service;


import com.example.dummyjson.dto.Product;
import com.example.dummyjson.dto.ProductResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(productService, "baseUrl", "https://dummyjson.com/products");
    }

    @Test
    void testGetAllProducts() {
        Product product1 = new Product(1L, "Product 1", "", 0.0);

        Product product2 = new Product(2L, "Product 2", "", 0.0);

        List<Product> productList = Arrays.asList(product1, product2);

        ProductResponse productResponse = new ProductResponse(productList);

        when(restTemplate.getForObject("https://dummyjson.com/products", ProductResponse.class)).thenReturn(productResponse);

        List<Product> result = productService.getAllProducts().getBody();

        assertEquals(2, result.size());

        assertEquals("Product 1", result.getFirst().getTitle());
    }

    @Test
    void testGetProductById() {
        Product product = new Product(1L, "Product 1", "", 0.0);

        when(restTemplate.getForObject("https://dummyjson.com/products/1", Product.class)).thenReturn(product);

        Product result = productService.getProductById(1L);
        assertEquals("Product 1", result.getTitle());
    }
}

package com.example.dummyjson.controller;

import com.example.dummyjson.dto.Product;
import com.example.dummyjson.service.ProductWebClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class ProductWebClientControllerTest {

    @InjectMocks
    private ProductWebClientController controller;

    @Mock
    private ProductWebClientService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllProducts_Success() {
        List<Product> mockProducts = List.of(new Product(1L, "Product 1", "Lorem ipsum", 100.0));
        when(service.getAllProductsByWebClient())
                .thenReturn(ResponseEntity.ok(mockProducts));

        ResponseEntity<List<Product>> response = controller.getAllProducts();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockProducts, response.getBody());
    }

    @Test
    void getProductById_Success() {
        Product mockProduct = new Product(1L, "Product 1", "Lorem ipsum", 100.0);
        when(service.getByWebClientProductById(1L))
                .thenReturn(mockProduct);

        Product product = controller.getProductById(1L);

        assertEquals(mockProduct, product);
    }

    @Test
    void getProductById_NotFound() {
        // Arrange
        WebClientResponseException notFoundException = WebClientResponseException.create(
                404, "Not Found", null, null, null);

        when(service.getByWebClientProductById(1L))
                .thenThrow(notFoundException);

        // Act & Assert
        assertThrows(WebClientResponseException.NotFound.class, () -> controller.getProductById(1L));
    }
}

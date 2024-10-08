package com.example.dummyjson.dto;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ProductTest {

    @Test
    void testGetAndSetter() {
        Long expectId = 1L;
        String expectedTitle = "A dummy title";
        String expectedDescription = "A dummy description";
        Double expectedPrice = 2.1;

        Product product1 = new Product(1L, "A dummy title", "A dummy description", 2.1);

        assertEquals(expectId, product1.getId());
        assertEquals(expectedTitle, product1.getTitle());
        assertEquals(expectedDescription, product1.getDescription());
        assertEquals(expectedPrice, product1.getPrice());
    }
}

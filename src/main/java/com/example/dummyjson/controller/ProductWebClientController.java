package com.example.dummyjson.controller;

import com.example.dummyjson.dto.Product;
import com.example.dummyjson.service.ProductWebClientService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/web/api/products")
@RequiredArgsConstructor
public class ProductWebClientController {

    private final ProductWebClientService service;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return service.getAllProductsByWebClient();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") @NotNull Long id) {
        return service.getByWebClientProductById(id);
    }


}

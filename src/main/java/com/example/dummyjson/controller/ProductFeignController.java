package com.example.dummyjson.controller;

import com.example.dummyjson.dto.Product;
import com.example.dummyjson.service.ProductFeignService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Product FeignClient", description = "Endpoints utilizando FeignClient")
@RestController
@RequestMapping("/feign/api/products")
@RequiredArgsConstructor
public class ProductFeignController {

    private final ProductFeignService service;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return service.getAllProductsByWebClient();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") @NotNull Long id) {
        return service.getByWebClientProductById(id);
    }


}

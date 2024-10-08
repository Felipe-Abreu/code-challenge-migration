package com.example.dummyjson.config;

import com.example.dummyjson.dto.Product;
import com.example.dummyjson.dto.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "FeignDummyJson", url = "${app.base_url_dummyjson}")
public interface FeignProduct {

    @GetMapping
    ProductResponse getAllProducts();

    @GetMapping("/{id}")
    Product getProductById(@PathVariable("id") Long id);

}

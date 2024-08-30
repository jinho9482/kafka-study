package com.example.product.query.controller;

import com.example.product.query.entity.Product;
import com.example.product.query.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<Product> getAll() {
        return productService.getAll();
    }

    // String을 쓰는 것이 오류가 덜 난다.
    @GetMapping("/{id}")
    public Product getById(@PathVariable String id) {
        return productService.getById(UUID.fromString(id));
    }

}

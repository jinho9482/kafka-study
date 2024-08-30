package com.example.product.query.repository;

import com.example.product.query.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    void save() {
        UUID inputId = UUID.randomUUID();
        Product product = Product.builder().id(inputId).name("Jinho").qty(1).build();
        productRepository.save(product);

        UUID outputId = productRepository.findAll().get(0).getId();
        System.out.println(inputId + "\n" + outputId);

    }
}
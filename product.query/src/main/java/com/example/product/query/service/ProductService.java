package com.example.product.query.service;

import com.example.product.query.entity.KafkaStatus;
import com.example.product.query.entity.Product;
import com.example.product.query.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getById(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));
    }
    public void save(Product product) {
        productRepository.save(product);
    }
    public void deleteById(UUID id) {
        productRepository.deleteById(id);
    }

    @KafkaListener(topics = "product-topic100", id = "cafe")
    @Transactional
    public void listen(KafkaStatus<Product> status) {
        switch (status.status()) {
            case "insert" -> save(status.data());
            case "delete" -> deleteById(status.data().getId());
            case "update" -> {
                Product product = productRepository.findById(status.data().getId()).orElseThrow();
                product.setQty(product.getQty() + status.data().getQty());
            }
        }
    }
}

package com.example.product.command.controller;

import com.example.product.command.entity.Product;
import com.example.product.command.kafka.dto.KafkaStatus;
import com.example.product.command.kafka.producer.ProductProducer;
import com.example.product.command.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductProducer productProducer;
    private final List<KafkaStatus<Product>> list = new ArrayList<>();

    // Product.builder().id(UUID.fromString(id)).build(), "delete" command에서는 이 상태를 저장한다.
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") String id) {
        Product product = Product.builder().id(UUID.fromString(id)).build();
        KafkaStatus<Product> productKafkaStatus = new KafkaStatus<>(product, "delete");
        list.add(productKafkaStatus);
        productProducer.send(
                product,
                "delete");
        list.remove(productKafkaStatus);
    }

    @PostMapping
    public void insert(@RequestBody Product product) {
        KafkaStatus<Product> productKafkaStatus = new KafkaStatus<>(product, "insert");
        list.add(productKafkaStatus);
        productProducer.send(product, "insert");
        list.remove(productKafkaStatus);
    }

    @PutMapping("{id}/buy")
    public void buy(@PathVariable("id") String id) {
        Product build = Product.builder().id(UUID.fromString(id)).qty(-1).build();
        KafkaStatus<Product> productKafkaStatus = new KafkaStatus<>(build, "update");
        list.add(productKafkaStatus);
        productProducer.send(
                build,
                "update"
        );
        list.remove(productKafkaStatus);

    }

    @PutMapping("{id}/sell")
    public void sell(@PathVariable("id") String id) {
        Product build = Product.builder().id(UUID.fromString(id)).qty(1).build();
        KafkaStatus<Product> productKafkaStatus = new KafkaStatus<>(build, "update");
        list.add(productKafkaStatus);
        productProducer.send(
                build,
                "update"
        );
        list.remove(productKafkaStatus);
    }
    @Scheduled(cron = "*/5 * * * * *")
    public void send() {
        List<KafkaStatus<Product>> success = new ArrayList<>();
        success.forEach(el -> {
            productProducer.send(el.data(), el.status());
            success.add(el);
        });
        success.forEach(list::remove);

    }

}

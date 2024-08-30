package com.example.product.command.kafka.producer;

import com.example.product.command.entity.Product;
import com.example.product.command.kafka.dto.KafkaStatus;
import lombok.RequiredArgsConstructor;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductProducer {
    private final KafkaTemplate<String, KafkaStatus<Product>> kafkaTemplate;

    @Value("${kafka.product.name}") private String name;
    @Bean // spring이 관리해줘야 하기 때문에 붙임
    private NewTopic newTopic() {
        return new NewTopic(name, 1, (short) 1);
    }

    public void send(Product product, String status) {
        KafkaStatus<Product> kafkaStatus = new KafkaStatus<>(product, status);
        kafkaTemplate.send(name, kafkaStatus);
    }
}

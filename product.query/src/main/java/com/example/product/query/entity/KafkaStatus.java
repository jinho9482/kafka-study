package com.example.product.query.entity;

public record KafkaStatus<T>(
         T data, String status
) {
}

package com.example.product.command.kafka.dto;

public record KafkaStatus<T>(
         T data, String status
) {
}

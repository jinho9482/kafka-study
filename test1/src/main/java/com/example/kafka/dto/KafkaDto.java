package com.example.kafka.dto;

public record KafkaDto<T> (String status, T data) {
}

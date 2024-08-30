package com.example.product.command.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Table(name = "PRODUCT")
public class Product {
    @Id
//    @GeneratedValue (strategy = GenerationType.UUID) // 이걸 쓰면 내가 넣을 수 없다.
    private UUID id;
    private String name;
    private Integer qty;

}

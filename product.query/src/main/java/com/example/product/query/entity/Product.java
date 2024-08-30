package com.example.product.query.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Service;

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
    @Setter
    private Integer qty;

}

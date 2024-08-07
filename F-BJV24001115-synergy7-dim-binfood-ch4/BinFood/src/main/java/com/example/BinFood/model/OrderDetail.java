package com.example.BinFood.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Data
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_detail")
public class OrderDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "order_id",nullable = false)
    private Order orderId;

    @ManyToOne
    @JoinColumn(name = "product_id",nullable = false)
    private Product productId;

    private double total_price;
}

package com.example.BinarFood.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private UUID id;
    private LocalDateTime orderTime;
    private String destinationAddress;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    private boolean completed;
}

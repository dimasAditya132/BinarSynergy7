package com.example.BinarFood.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "merchant")
public class Merchant {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private UUID id;

    @Setter
    @Getter
    private String merchantName;
    @Setter
    @Getter
    private String merchantLocation;
    @Column(name = "open",nullable = false)
    private Boolean open;

    public String getStatus(){
        return open ? "Open" : "Closed";
    }
}

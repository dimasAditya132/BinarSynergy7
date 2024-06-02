package com.example.BinFood.model;

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
    private UUID id;

    private String merchantName;

    private String merchantLocation;

    private boolean open = Boolean.FALSE;

    @OneToMany(mappedBy = "merchant", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Product> productList;
}

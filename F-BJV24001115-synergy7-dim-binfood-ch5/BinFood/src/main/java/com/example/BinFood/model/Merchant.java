package com.example.BinFood.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "merchant")
public class Merchant implements Serializable {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    @Column(unique = true)
    private String merchantName;
    private String merchantLocation;
    private boolean status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "merchant", orphanRemoval = true)
    private List<Product> products;
}

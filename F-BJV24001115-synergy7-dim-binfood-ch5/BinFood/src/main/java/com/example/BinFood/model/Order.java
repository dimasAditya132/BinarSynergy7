package com.example.BinFood.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders", uniqueConstraints = { @UniqueConstraint(columnNames = {"user_id","time"}) })
public class Order implements Serializable {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    private Date orderTime;
    private String destinationAddress;
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;
    private boolean status;
}

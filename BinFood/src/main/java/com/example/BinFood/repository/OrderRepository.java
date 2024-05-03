package com.example.BinFood.repository;

import com.example.BinFood.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Product, UUID>{
}

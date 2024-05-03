package com.example.BinarFood.repository;

import com.example.BinarFood.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, UUID> {
    Optional<Merchant> findMerchantByMerchantName(String name);
    List<Merchant> findByOpenTrue();

}
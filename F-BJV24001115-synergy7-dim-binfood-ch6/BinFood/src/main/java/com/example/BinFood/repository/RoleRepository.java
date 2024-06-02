package com.example.BinFood.repository;

import com.example.BinFood.model.accounts.ERole;
import com.example.BinFood.model.accounts.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    Role findByName(ERole name);
}

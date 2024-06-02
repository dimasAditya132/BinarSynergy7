package com.example.BinFood.repository;

import com.example.BinFood.model.Order;
import com.example.BinFood.model.accounts.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findAllByUser(User user);

    List<Order> findByOrderTimeBetween(Date startDate, Date endDate);
}

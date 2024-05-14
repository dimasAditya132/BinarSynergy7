package com.example.BinFood.repository;

import com.example.BinFood.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String>{
    //Query untuk mengambil data order berdasarkan user
    @Query(nativeQuery = true, value = "SELECT * FROM orders o where o.user_id = (select id from users u where u.username = :username)")
    List<Order> getOrdersOfUser(@Param("username") String username);
}

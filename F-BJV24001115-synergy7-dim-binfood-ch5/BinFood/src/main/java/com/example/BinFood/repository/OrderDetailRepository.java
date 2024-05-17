package com.example.BinFood.repository;

import com.example.BinFood.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
    @Query(nativeQuery = true, value = "SELECT * FROM order_detail WHERE order_id = :oid")
    List<OrderDetail> querySelectDetail(@Param("oid") UUID orderID);
}

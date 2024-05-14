package com.example.BinFood.service;

import com.example.BinFood.model.Order;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public interface OrderService {
    Order orderBuilder(String username, Date time, String destination, boolean stat);
    void addOrderToDB(Order confirmedOrders);
    void editOrderDestination(String newDestination);
    void editOrderStatus(Boolean newStatus);
    void deleteOrder(Order order);


}

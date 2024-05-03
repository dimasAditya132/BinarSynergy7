package com.example.BinFood.service;

import com.example.BinFood.model.Order;
import com.example.BinFood.model.Product;
import com.example.BinFood.model.User;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    Order createOrder(User user, Product product, int quantity);
    List<Order> getAllOrders();
    Order getOrderById(UUID id);
}

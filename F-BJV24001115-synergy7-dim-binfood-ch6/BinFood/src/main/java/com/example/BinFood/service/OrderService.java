package com.example.BinFood.service;

import com.example.BinFood.model.Order;
import com.example.BinFood.model.accounts.User;

import java.util.Date;
import java.util.List;

public interface OrderService {
    Order insertOrder(Order order);

    Order getOrder(String orderId);

    List<Order> getAllOrder();

    List<Order> getAllOrderByUser(User user);

    List<Order> getAllOrderInBetween(Date startDate, Date endDate);

    Order updateOrder(Order order);

    void hardDeleteOrder(Order order);

}

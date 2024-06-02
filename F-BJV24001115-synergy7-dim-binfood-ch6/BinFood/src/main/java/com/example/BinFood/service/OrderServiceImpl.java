package com.example.BinFood.service;

import com.example.BinFood.model.Order;
import com.example.BinFood.model.accounts.User;
import com.example.BinFood.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Override
    public Order insertOrder(Order order) {
        order = orderRepository.save(order);
        log.info("Order data with id" + order.getId() + " succesfully created");
        return order;
    }

    @Override
    public Order getOrder(String orderId) {
        UUID uuid = UUID.fromString(orderId);
        Optional<Order> order = orderRepository.findById(uuid);
        if (order.isEmpty()) {
            throw new RuntimeException();
        }
        return order.get();
    }

    @Override
    public List<Order> getAllOrder() {
        List<Order> orderList = orderRepository.findAll();
        if (orderList.isEmpty()) {
            return Collections.emptyList();
        }
        return orderList;
    }

    @Override
    public List<Order> getAllOrderByUser(User user) {
        List<Order> orderList = orderRepository.findAllByUser(user);
        if (orderList.isEmpty()) {
            return Collections.emptyList();
        }
        return orderList;
    }

    @Override
    public List<Order> getAllOrderInBetween(Date startDate, Date endDate) {
        return orderRepository.findByOrderTimeBetween(startDate, endDate);
    }

    @Override
    public Order updateOrder(Order order) {
        orderRepository.save(order);
        return order;
    }

    @Override
    public void hardDeleteOrder(Order order) {
        orderRepository.delete(order);
    }
}

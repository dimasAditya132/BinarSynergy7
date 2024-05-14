package com.example.BinFood.service;

import com.example.BinFood.model.Order;
import com.example.BinFood.repository.OrderDetailRepository;
import com.example.BinFood.repository.OrderRepository;
import com.example.BinFood.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public Order orderBuilder(String username, Date time, String destination, boolean stat) {
        return Order.builder()
                .user(userRepository.queryFindUserByName(username))
                .orderTime(time)
                .destinationAddress(destination)
                .status(stat)
                .build();
    }

    @Override
    public void addOrderToDB(Order confirmedOrders) {
        orderRepository.save(confirmedOrders);
    }

    @Override
    public void editOrderDestination(String newDestination) {

    }

    @Override
    public void editOrderStatus(Boolean newStatus) {

    }

    @Override
    public void deleteOrder(Order order) {

    }
}

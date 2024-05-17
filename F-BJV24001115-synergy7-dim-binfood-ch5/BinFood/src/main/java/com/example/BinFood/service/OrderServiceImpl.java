package com.example.BinFood.service;

import com.example.BinFood.model.Order;
import com.example.BinFood.repository.OrderDetailRepository;
import com.example.BinFood.repository.OrderRepository;
import com.example.BinFood.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
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
    public boolean addOrderToDB(Order order) {
        Order confirmedOrders = Optional.of(order)
                .filter(order1 -> Objects.nonNull(order1.getUser()) &&
                        Objects.nonNull(order1.getOrderTime()) &&
                        order1.getDestinationAddress() != null)
                .orElse(null);
        try {
            log.info("Adding order to Database");
            assert confirmedOrders != null;
            orderRepository.save(confirmedOrders);
            log.info("Order added to Database");
            return true;
        } catch (Exception e) {
            log.error("Failed to add order to Database");
            return false;
        }
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

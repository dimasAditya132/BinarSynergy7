package com.example.BinFood.service;

import com.example.BinFood.model.Order;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public interface OrderService {
    Order orderBuilder(String username, Date time, String destination, boolean stat);

    boolean addOrderToDB(Order order);

    //TODO : To Be Announced
    void editOrderDestination(String newDestination);

    void editOrderStatus(Boolean newStatus);

    void deleteOrder(Order order);


}

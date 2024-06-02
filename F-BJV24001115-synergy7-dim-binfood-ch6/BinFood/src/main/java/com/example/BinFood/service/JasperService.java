package com.example.BinFood.service;

import com.example.BinFood.model.Order;
import com.example.BinFood.model.OrderDetail;
import com.example.BinFood.model.accounts.User;

import java.util.List;

public interface JasperService {
    byte[] generate(List<OrderDetail> orderDetailList, User user, Order order, String format);
}

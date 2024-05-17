package com.example.BinFood.service;

import com.example.BinFood.model.Order;
import com.example.BinFood.model.OrderDetail;
import com.example.BinFood.model.response.OrderDetailResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderDetailService {
    double finalPrice(double product_price, int quantity);

    double totalPriceInCart(List<OrderDetailResponse> userCart);

    OrderDetail buildOrderDetail(int quantity, double total_price, Order order, String productName, String merchantName);

    boolean addOrderDetailToDB(OrderDetail orderDetail);

}

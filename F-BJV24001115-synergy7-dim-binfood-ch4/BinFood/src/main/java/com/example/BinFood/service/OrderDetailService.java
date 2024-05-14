package com.example.BinFood.service;

import com.example.BinFood.model.OrderDetail;
import com.example.BinFood.model.response.OrderDetailResponse;
import com.example.BinFood.model.response.ProductResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderDetailService {
    double finalPrice(ProductResponse productResponse, int quantity);
    double totalPriceInCart(List<OrderDetailResponse> userCart);
    OrderDetail buildOrderDetail(int quantity, double total, String username, String productName, String merchantName);
    void addOrderDetailToDB(OrderDetail orderDetail);

}

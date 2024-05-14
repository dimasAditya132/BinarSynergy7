package com.example.BinFood.service;

import com.example.BinFood.model.OrderDetail;
import com.example.BinFood.model.response.OrderDetailResponse;
import com.example.BinFood.model.response.ProductResponse;
import com.example.BinFood.repository.OrderDetailRepository;
import com.example.BinFood.repository.OrderRepository;
import com.example.BinFood.repository.ProductRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public double finalPrice(ProductResponse productResponse, int quantity) {
        return productResponse.getProductPrice() * quantity;
    }

    @Override
    public double totalPriceInCart(List<OrderDetailResponse> userCart) {
        return userCart.stream().reduce(0.0, (aDouble, orderDetailResponse) -> aDouble + orderDetailResponse.getProductFinalPrice(), Double::sum);
    }

    @Override
    public OrderDetail buildOrderDetail(int quantity, double total, String username, String productName, String merchantName) {
        return OrderDetail.builder()
                .quantity(quantity)
                .total_price(total)
                .orderId(orderRepository.getOrdersOfUser(username).get(orderRepository.getOrdersOfUser(username).size() - 1))
                .productId(productRepository.queryOneFromMerchant(productName, merchantName))
                .build();
    }

    @Override
    public void addOrderDetailToDB(OrderDetail orderDetail) {
        orderDetailRepository.save(orderDetail);

    }
}

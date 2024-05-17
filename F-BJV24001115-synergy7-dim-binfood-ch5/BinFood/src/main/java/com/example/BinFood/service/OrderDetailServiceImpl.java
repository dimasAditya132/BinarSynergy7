package com.example.BinFood.service;

import com.example.BinFood.model.Order;
import com.example.BinFood.model.OrderDetail;
import com.example.BinFood.model.response.OrderDetailResponse;
import com.example.BinFood.repository.OrderDetailRepository;
import com.example.BinFood.repository.OrderRepository;
import com.example.BinFood.repository.ProductRepository;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Getter
@Slf4j
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
    public double finalPrice(double product_price, int quantity) {
        return product_price * quantity;
    }

    @Override
    public double totalPriceInCart(List<OrderDetailResponse> userCart) {
        return userCart.stream().reduce(0.0, (aDouble, orderDetailResponse) -> aDouble + orderDetailResponse.getProductFinalPrice(), Double::sum);
    }

    @Override
    public OrderDetail buildOrderDetail(int quantity,
                                        double total_price,
                                        Order order,
                                        String productName,
                                        String merchantName) {
        return OrderDetail.builder()
                .quantity(quantity)
                .total_price(total_price)
                .orderId(order)
                .productId(productRepository.queryOneFromMerchant(productName, merchantName))
                .build();
    }


    @Override
    public boolean addOrderDetailToDB(OrderDetail orderDetail) {
        OrderDetail orderDetails = Optional.ofNullable(orderDetail)
                .filter(detail1 -> detail1.getQuantity() != 0 &&
                        detail1.getTotal_price() != 0 &&
                        Objects.nonNull(detail1.getOrderId()) &&
                        Objects.nonNull(detail1.getProductId()))
                .orElse(null);
        try {
            log.info("Adding detail to database...");
            assert orderDetails != null;
            orderDetailRepository.save(orderDetails);
            log.info("Successfully added detail to database");
            return true;
        } catch (Exception e) {
            log.error("Failed to add detail to database");
            log.info("Cause " + e.getMessage());
            return false;
        }
    }
}

package com.example.BinFood.controller;

import com.example.BinFood.model.Order;
import com.example.BinFood.model.OrderDetail;
import com.example.BinFood.model.Product;
import com.example.BinFood.model.accounts.User;
import com.example.BinFood.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class OrderUtil {
    private final OrderService orderService;
    private final OrderDetailService orderDetailService;
    private final UserService userService;
    private final ProductService productService;
    private final JasperService jasperService;

    public OrderUtil(OrderService orderService, OrderDetailService orderDetailService, UserService userService, ProductService productService, JasperService jasperService) {
        this.orderService = orderService;
        this.orderDetailService = orderDetailService;
        this.userService = userService;
        this.productService = productService;
        this.jasperService = jasperService;
    }

    public Order createOrder(String username, String destinationAddress, List<OrderDetail> orderDetailList) {
        User user;
        try {
            user = userService.getUserByUsername(username);
        } catch (RuntimeException e) {
            log.error(e.getLocalizedMessage());
            throw e;
        }

        Order order = Order.builder()
                .user(user)
                .orderTime(Date.from(Instant.now()))
                .destinationAddress(destinationAddress)
                .completed(Boolean.FALSE)
                .build();
        order = orderService.insertOrder(order);

        for (OrderDetail orderDetail : orderDetailList) {
            orderDetail.setOrder(order);
        }
        orderDetailList = orderDetailService.createBatchesOrder(orderDetailList);
        order.setOrderDetailList(orderDetailList);
        return order;
    }

    public OrderDetail createOrderDetail(String productId, int quantity) {
        Product product = productService.getProductById(productId);
        OrderDetail orderDetail = OrderDetail.builder()
                .product(product)
                .quantity(quantity)
                .totalPrice(product.getPrice() * quantity)
                .build();
        log.info("OrderDetail created: {}" + product.getProductName() + " quantity " + quantity);
        return orderDetail;
    }

    public OrderDetail createOrderDetail(Product product, int quantity) {
        OrderDetail orderDetail = OrderDetail.builder()
                .product(product)
                .quantity(quantity)
                .totalPrice(product.getPrice() * quantity)
                .build();
        log.info("OrderDetail created: {}" + product.getProductName() + " quantity " + quantity);
        return orderDetail;
    }

    public List<Order> getAllOrder() {
        List<Order> orderList = orderService.getAllOrder();
        orderList.forEach(order ->
                log.info(order.getId() + " | " + order.getOrderTime() + " | " + order.getUser().getUsername() + " | " + order.getDestinationAddress())
        );
        return orderList;
    }

    public List<Order> getAllOrderByUser(String username) {
        User user = userService.getUserByUsername(username);
        List<Order> orderList = orderService.getAllOrderByUser(user);
        orderList.forEach(order ->
                log.info(order.getId() + " | " + order.getOrderTime() + " | " + order.getUser().getUsername() + " | " + order.getDestinationAddress())
        );
        return orderList;
    }

    public List<OrderDetail> getAllOrderDetail() {
        List<OrderDetail> orderDetailList = orderDetailService.getAllOrderDetail();
        orderDetailList.forEach(orderDetail ->
                log.info(orderDetail.getId().toString())
        );
        return orderDetailList;
    }

    public List<OrderDetail> getAllOrderDetailPageable(int pageNumber, int pageAmount) {
        List<OrderDetail> orderDetailList = orderDetailService.getAllOrdersDetailPageable(pageNumber, pageAmount);
        orderDetailList.forEach(orderDetail ->
                log.info(orderDetail.getProduct().getProductName() + " | " + orderDetail.getQuantity() + " | " + orderDetail.getTotalPrice())
        );
        return orderDetailList;
    }

    public Order getOrderDetail(String orderId) {
        Order order = orderService.getOrder(orderId);
        order.getOrderDetailList().forEach(orderDetail ->
                log.info(orderDetail.getProduct().getProductName() + " | " + orderDetail.getQuantity() + " | " + orderDetail.getTotalPrice())
        );
        return order;
    }

    public void editOrderStatus(String orderId, boolean completedStatus) {
        Order order = orderService.getOrder(orderId);
        order.setCompleted(completedStatus);
        orderService.updateOrder(order);
        log.debug("Order status updated to: " + completedStatus);
    }

    public void deleteOrder(String orderId) {
        Order order = orderService.getOrder(orderId);
        orderService.hardDeleteOrder(order);
    }
}

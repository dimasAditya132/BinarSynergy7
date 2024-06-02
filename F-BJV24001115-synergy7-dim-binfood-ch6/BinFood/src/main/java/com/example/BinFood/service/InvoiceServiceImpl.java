package com.example.BinFood.service;

import com.example.BinFood.model.Order;
import com.example.BinFood.model.OrderDetail;
import com.example.BinFood.model.accounts.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class InvoiceServiceImpl implements InvoiceService {
    final
    OrderService orderService;

    final
    UserService userService;
    final
    JasperService jasperService;

    public InvoiceServiceImpl(OrderService orderService, UserService userService, JasperService jasperService) {
        this.orderService = orderService;
        this.userService = userService;
        this.jasperService = jasperService;
    }

    @Override
    public byte[] generateInvoice(String userId, String orderId) {
        User user;
        Order order;
        byte[] invoice = null;

        try {
            user = userService.getUserById(userId);
            order = orderService.getOrder(orderId);
            List<OrderDetail> orderDetailList = order.getOrderDetailList();
            invoice = jasperService.generate(orderDetailList, user, order, "pdf");
        } catch (RuntimeException e) {
            log.error(e.getLocalizedMessage());
        }
        return invoice;
    }
}

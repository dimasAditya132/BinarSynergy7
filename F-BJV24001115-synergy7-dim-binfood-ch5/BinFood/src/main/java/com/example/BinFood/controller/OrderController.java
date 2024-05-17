package com.example.BinFood.controller;

import com.example.BinFood.model.Order;
import com.example.BinFood.model.OrderDetail;
import com.example.BinFood.model.response.OrderResponse;
import com.example.BinFood.service.*;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.Date;

@RestController
@Slf4j
@RequestMapping("order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderDetailService orderDetailService;
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;
    @Autowired
    InvoiceService invoiceService;

    @PostMapping(value = "ordering/{username}", consumes = "application/json")
    public ResponseEntity<String> requestMakeOrder(@PathVariable("username") String username,
                                                   @RequestBody OrderResponse orderResponse) {
        Order orderUser = orderService.orderBuilder(username, new Date(), orderResponse.getOrderDestination(), false);

        if (orderService.addOrderToDB(orderUser)) {
            orderResponse.getOrderDetailResponseLists().forEach(orderDetailResponse -> {
                OrderDetail orderDetail = orderDetailService.buildOrderDetail(
                        orderDetailResponse.getProductQuantity(),
                        orderDetailService.finalPrice(orderDetailResponse.getProductPrice(), orderDetailResponse.getProductQuantity()),
                        orderUser,
                        orderDetailResponse.getProductName(),
                        orderResponse.getMerchantName());
                orderDetailService.addOrderDetailToDB(orderDetail);
            });
            return new ResponseEntity<>("Successfully added order", HttpStatus.OK);
        } else return new ResponseEntity<>("Failed to add order", HttpStatus.NOT_ACCEPTABLE);
    }

    @GetMapping(value = "ordered/{username}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity generateInvoice(@PathVariable("username") String username) throws JRException, FileNotFoundException {
        return ResponseEntity.ok().body(invoiceService.generateInvoice(username));
    }
}

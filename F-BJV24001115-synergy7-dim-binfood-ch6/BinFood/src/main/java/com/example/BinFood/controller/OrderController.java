package com.example.BinFood.controller;

import com.example.BinFood.model.Order;
import com.example.BinFood.model.OrderDetail;
import com.example.BinFood.model.accounts.User;
import com.example.BinFood.payload.OrderDTO;
import com.example.BinFood.payload.OrderDetailDTO;
import com.example.BinFood.payload.Response;
import com.example.BinFood.service.InvoiceService;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {
    final ModelMapper modelMapper;
    final OrderUtil orderUtil;
    final UserUtil userUtil;
    final InvoiceService invoiceService;

    public OrderController(ModelMapper modelMapper, OrderUtil orderUtil, UserUtil userUtil, InvoiceService invoiceService) {
        this.modelMapper = modelMapper;
        this.orderUtil = orderUtil;
        this.userUtil = userUtil;
        this.invoiceService = invoiceService;
    }

    @PostMapping()
    public ResponseEntity<Response> add(@RequestBody OrderDTO orderDto) {
        List<OrderDetail> orderDetailList = orderDto.getOrderDetailList().stream()
                .map(orderDetailDto -> orderUtil.createOrderDetail(orderDetailDto.getId(), orderDetailDto.getQuantity()))
                .toList();
        try {
            Order order = orderUtil.createOrder(orderDto.getUserName(), orderDto.getDestinationAddress(), orderDetailList);
            return ResponseEntity.ok(new Response.Success(modelMapper.map(order, OrderDTO.class)));
        } catch (RuntimeException e) {
            return new ResponseEntity<>(new Response.Error(e.getLocalizedMessage()), HttpStatus.OK);
        }
    }

    @GetMapping("{username}")
    public ResponseEntity<Response> getAllOrders(@PathVariable("username") String username) {
        List<Order> orderDetailList = userUtil.getUserDetailByUsername(username).getOrderList();
        List<OrderDTO> orderDtoList = orderDetailList.stream()
                .map(order -> modelMapper.map(order, OrderDTO.class))
                .toList();
        return ResponseEntity.ok(new Response.Success(orderDtoList));
    }

    @GetMapping()
    public ResponseEntity<Response> getOrdersById(@RequestParam("orderId") String orderId) {
        Order order = orderUtil.getOrderDetail(orderId);
        if (order != null) {
            return ResponseEntity.ok(new Response.Success(modelMapper.map(order, OrderDTO.class)));
        } else {
            return new ResponseEntity<>(new Response.Error("Order not found"), HttpStatus.OK);
        }
    }

    @GetMapping("page")
    public ResponseEntity<Response> getOrders(@RequestParam("pageNumber") Integer pageNumber, @RequestParam("pageAmount") Integer pageAmount) {
        List<OrderDetail> orderDetailList = orderUtil.getAllOrderDetailPageable(pageNumber, pageAmount);
        List<OrderDetailDTO> orderDetailDTOList = orderDetailList.stream()
                .map(orderDetail -> modelMapper.map(orderDetail, OrderDetailDTO.class)).toList();
        return ResponseEntity.ok(new Response.Success(orderDetailDTOList));
    }

    @GetMapping("generate/{username}")
    public ResponseEntity<Resource> getReport(@PathVariable("username") String username, @RequestParam("orderId") String orderId) {
        String userId;
        try {
            User user = userUtil.getUserDetailByUsername(username);
            userId = user.getId().toString();
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        byte[] reportContent = invoiceService.generateInvoice(userId, orderId);

        ByteArrayResource resource = new ByteArrayResource(reportContent);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(resource.contentLength())
                .header(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment().filename("order-list.pdf").build().toString())
                .body(resource);
    }
}

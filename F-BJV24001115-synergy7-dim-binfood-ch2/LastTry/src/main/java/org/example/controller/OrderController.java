package org.example.controller;

import org.example.Data;
import org.example.service.OrderService;
import org.example.view.MenuView;

public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    public static void displayMenu() {
        Data.displayMenu();
    }

    public void placeOrder() {
        orderService.placeOrder();
    }

    public void confirmAndPay() {
        orderService.confirmAndPay();
    }


    public static String displayHeader(){
        return MenuView.displayTitleHeader();
    }
}

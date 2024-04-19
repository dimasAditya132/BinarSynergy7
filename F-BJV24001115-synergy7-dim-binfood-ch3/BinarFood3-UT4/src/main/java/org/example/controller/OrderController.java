package org.example.controller;

import org.example.Data;
import org.example.model.Order;
import org.example.service.OrderService;
import org.example.service.OrderServiceImpl;
import org.example.view.MenuView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class OrderController {

    private static boolean isMenuInitialized = false;

    private static Optional<OrderService> orderService = Optional.of(new OrderServiceImpl(new ArrayList<>(), Data.getMenu(), new Scanner(System.in)));

    public static void initializeMenu() {

        if (!isMenuInitialized) {
            System.out.println("Menginisialisasi menu...");
            Data.initializeMenu();
            isMenuInitialized = true;
        } else {
            System.out.println("Menu sudah diinisialisasi.");
        }
    }

    public static void displayMenu() {
        OrderController.displayHeader();
        MenuView.displayMenu();
        MenuView.displayControlMenu();
    }

    public static String displayHeader() {
        return MenuView.separatorLine() + "\n" + MenuView.displayTitle() + "\n" + MenuView.separatorLine();
    }

    public static int calculateTotalPrice(List<Order> orders) {
        return orders.stream()
                .mapToInt(Order::calculateOrder)
                .sum();
    }

    public static void placeOrder(int itemIndex) {
        orderService.ifPresent(service -> service.placeOrder(itemIndex));
    }

    public static void confirmAndPay() {
        orderService.ifPresent(OrderService::confirmAndPay);
    }
}

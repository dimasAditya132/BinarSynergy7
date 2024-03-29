package org.example.view;

import org.example.models.Beverage;
import org.example.models.Food;
import org.example.models.Order;

import java.util.List;

public class OrderView {
    private OrderView() {
    }

    public static void displayOrderDetails(List<Order> orders) {
        System.out.println("=== Detail Pesanan ===");
        for (Order order : orders) {
            Object item = order.getItem();
            int quantity = order.getQuantity();
            int price = 0;
            String name = "";
            if (item instanceof Food) {
                Food foodItem = (Food) item;
                price = foodItem.getPrice();
                name = foodItem.getName();
            } else if (item instanceof Beverage) {
                Beverage beverageItem = (Beverage) item;
                price = beverageItem.getPrice();
                name = beverageItem.getName();
            }
            int subtotal = price * quantity;
            System.out.println(name + " " + price + " x " + quantity + " = " + subtotal);
        }
        int total = orders.stream().mapToInt(Order::calculateTotalPrice).sum();
        System.out.println("Total Harga : Rp. " + total);
    }
}

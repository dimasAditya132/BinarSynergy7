package org.example.view;

import lombok.Getter;
import org.example.model.Menu;
import org.example.model.Order;

import java.util.List;

@Getter
public class OrderView {

    public static void displayOrderDetails(List<Order> orders){
        System.out.println("=== Detail Pesanan ===");
        orders.forEach(order -> {
            Menu item = order.getItem();
            int quantity = order.getQuantity();
            int price = 0;
            String name = "";
            if (item != null) {
                price = item.getPrice();
                name = item.getName();
            }
            System.out.println(quantity + " " + name + " @ Rp " + price + ",-"); // Output: 1 Nasi Goreng @ Rp 15000,-
        });
    }
}

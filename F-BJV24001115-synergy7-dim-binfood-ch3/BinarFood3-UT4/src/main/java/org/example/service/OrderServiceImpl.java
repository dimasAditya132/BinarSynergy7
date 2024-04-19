package org.example.service;


import org.example.controller.OrderController;
import org.example.model.Menu;
import org.example.model.Order;
import org.example.view.OrderView;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {
    private List<Order> orders;
    private List<Menu> menu;
    private Scanner scanner;
    private Menu selectedItem;

    public OrderServiceImpl(List<Order> orders, List<Menu> menu, Scanner scanner) {
        this.orders = orders;
        this.menu = menu;
        this.scanner = scanner;
    }

    @Override
    public void placeOrder(int itemIndex) {
        // Use Optional to handle potential null values
        Optional<Menu> optionalItem = Optional.ofNullable(menu.get(itemIndex - 1));

        optionalItem.ifPresent(item -> {
            System.out.print("Berapa pesanan anda: ");
            int quantity = scanner.nextInt();
            if (quantity > 0) {
                Optional<Order> existingOrder = orders.stream()
                        .filter(order -> order.getItem().getName().equals(item.getName()))
                        .findFirst();

                Order order = existingOrder.map(o -> {
                    o.setQuantity(o.getQuantity() + quantity);
                    return o;
                }).orElseGet(() -> new Order(item, quantity));

                System.out.println("Harga total untuk " + item.getName() + " is: " + order.calculateOrder());
                orders.add(order);
            } else {
                System.out.println("Kuantitas tidak valid. Silakan coba lagi.");
            }
        });
    }


    @Override
    public void confirmAndPay() {
        Optional.ofNullable(orders)
                .filter(orderList -> !orderList.isEmpty())
                .ifPresentOrElse(orderList -> {
                    OrderView.displayOrderDetails(orderList);
                    System.out.println("Total harga: " + OrderController.calculateTotalPrice(orderList));
                    System.out.println("1. Konfirmasi pembayaran");
                    System.out.println("2. Kembali ke menu utama");
                    System.out.println("3. Keluar");
                    System.out.print("Pilih: ");
                    if (!scanner.hasNextInt()) {
                        throw new NoSuchElementException("No next integer in the input");
                    }
                    int choice = scanner.nextInt();
                    if (choice == 1) {
                        System.out.println("Pembayaran berhasil!");
                        saveOrderHistory();
                        System.exit(0);
                    } else if (choice == 2) {
                        OrderController.displayMenu();
                    } else {
                        System.out.println("Keluar");
                    }
                }, () -> {
                    throw new NoSuchElementException("Belum ada pesanan yang dibuat!");
                });
    }

@Override
public void saveOrderHistory() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmmss_ddMMyyyy");
    String formattedDateTime = LocalDateTime.now().format(formatter);
    String fileName = "src/main/java/org/example/order_history_" + formattedDateTime + ".txt";
    try (FileWriter writer = new FileWriter(fileName, true)) {
        writer.write(OrderController.displayHeader() + "\n");
        Optional.ofNullable(orders).ifPresent(orderList -> {
            String orderDetails = orderList.stream()
                    .map(order -> {
                        Menu item = order.getItem();
                        String name = "";
                        if (item != null) {
                            name = item.getName();
                        }
                        return String.format("%-20s @ %d, Rp. %d\tRp. %d", name, order.getQuantity(), item.getPrice(), order.calculateOrder());
                    })
                    .collect(Collectors.joining("\n"));
            try {
                writer.write(orderDetails + "\n");
                writer.write("===============================\n"); // separator for each order
                int totalAllOrders = orderList.stream().mapToInt(Order::calculateOrder).sum();
                writer.write(String.format("Total Harga: Rp. %d", totalAllOrders) + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    } catch (IOException e) {
        System.out.println("Terjadi kesalahan saat menyimpan riwayat pesanan.");
        e.printStackTrace();
    }
    orders.clear();
}

    @Override
    public int calculateTotalPrice() {
        return OrderController.calculateTotalPrice(orders);
    }

    @Override
    public void placeOrder() {
        // Implementation of placeOrder method goes here
    }
}
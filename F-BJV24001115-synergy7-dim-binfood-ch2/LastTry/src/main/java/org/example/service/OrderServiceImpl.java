package org.example.service;

import org.example.controller.OrderController;
import org.example.models.Beverage;
import org.example.models.Food;
import org.example.models.Order;
import org.example.view.Helper;
import org.example.view.MenuView;
import org.example.view.OrderView;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class OrderServiceImpl implements OrderService {
    private List<Order> orders;
    private List<Object> menu;
    private Scanner scanner;
    private Object selectedItem;

    public OrderServiceImpl(Scanner scanner, List<Object> menu) {
        this.scanner = scanner;
        this.menu = menu;
        this.orders = new ArrayList<>();
    }

    @Override
    public void placeOrder() {
        System.out.println("Pilih menu (1-5): ");
        int itemIndex = scanner.nextInt();
        if (itemIndex < 1 || itemIndex > 5) {
            System.out.println("Menu tidak tersedia!");
            return;
        }

        System.out.println("Masukkan jumlah pesanan: ");
        int quantity = scanner.nextInt();
        if (quantity < 1) {
            System.out.println("Jumlah pesanan tidak valid!");
            return;
        }

        Object selectedItem = menu.get(itemIndex - 1);
        Order order = new Order(selectedItem, quantity);
        orders.add(order);
        System.out.println("Pesanan berhasil ditambahkan!");
    }

    @Override
    public void confirmAndPay() {
        if (orders.isEmpty()) {
            System.out.println("Belum ada pesanan yang dipilih!");
            return;
        }
        OrderView.displayOrderDetails(orders);

        System.out.println("Pilihan: ");
        System.out.println("1. Konfirmasi & Bayar");
        System.out.println("2. Kembali ke Menu Utama: ");
        System.out.println("3. Keluar: ");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                saveOrderHistory();
                break;
            case 2:
                //kembali ke menu utama
                break;
            case 3:
                System.out.println("Keluar aplikasi...");
                break;
            default:
                System.out.println("Pilihan tidak tersedia!");
        }
    }

    @Override
    public void saveOrderHistory() {
        String fileName = "order_history_" + System.currentTimeMillis() + ".txt";
        try (FileWriter writer = new FileWriter(fileName)) {

            for (Order order : orders) {
                Object item = order.getItem();
                String name = "";
                if (item instanceof Food) {
                    Food foodItem = (Food) item;
                    name = foodItem.getName();
                } else if (item instanceof Beverage) {
                    Beverage beverageItem = (Beverage) item;
                    name = beverageItem.getName();
                }
                writer.write(OrderController.displayHeader() + "\n");
                writer.write(name + ", " + order.getQuantity() + ", " + order.getPrice() + "\n");
                writer.write("Total Harga: " + order.calculateTotalPrice() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

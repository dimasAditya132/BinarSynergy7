package org.example;

import org.example.controller.OrderController;
import org.example.service.OrderServiceImpl;

import java.util.InputMismatchException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        Data.initializeMenu();

        OrderServiceImpl orderService = new OrderServiceImpl(scanner, Data.getMenu());
        OrderController orderController = new OrderController(orderService);

        boolean exit = false;

//        while (!exit) {
//            OrderController.displayHeader();
//            System.out.println("1. Lihat Menu");
//            System.out.println("2. Pesan Menu");
//            System.out.println("3. Konfirmasi dan Bayar");
//            System.out.println("4. Keluar");
//
//            try {
//                System.out.print("Pilih : ");
//                int choice = scanner.nextInt();
//                switch (choice) {
//                    case 1:
//                        Data.displayMenu();
//                        break;
//                    case 2:
//                        orderController.placeOrder();
//                        break;
//                    case 3:
//                        orderController.confirmAndPay();
//                        break;
//                    case 4:
//                        exit = true;
//                        break;
//                    default:
//                        System.out.println("Pilihan tidak ada");
//                }
//            } catch (InputMismatchException e) {
//                System.out.println("Input harus berupa angka!");
//                scanner.next();
//            }
//        }
        while (!exit) {
            OrderController.displayHeader();
            OrderController.displayMenu();
            System.out.println("99. Confirm & Pay");
            System.out.println("0. Close App");

            try {
                System.out.print("Pilih : ");
                int choice = scanner.nextInt();
                if (choice >= 1 && choice <= 5) {
                    orderController.placeOrder();
                } else if (choice == 99) {
                    orderController.confirmAndPay();
                } else if (choice == 0) {
                    exit = true;
                } else {
                    System.out.println("Pilihan tidak ada");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input harus berupa angka!");
                scanner.next();
            }
        }
        scanner.close();
    }
}
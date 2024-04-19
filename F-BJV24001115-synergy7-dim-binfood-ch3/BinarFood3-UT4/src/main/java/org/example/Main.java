package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.controller.OrderController;

import java.util.InputMismatchException;
import java.util.Scanner;


@Setter
@Getter
@AllArgsConstructor
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        OrderController.initializeMenu();
        boolean exit = false;
        while(!exit) {
            OrderController.displayMenu();
            boolean validInput = false;
            while (!validInput) {
                try{
                    System.out.print("Masukkan Pilihan Anda: ");
                    int pilihan = Integer.parseInt(scanner.nextLine());
                    int menuSize = Data.getMenu().size();
                    if (menuSize > 0 && pilihan >= 1 && pilihan <= menuSize){
                        OrderController.placeOrder(pilihan);
                        validInput = true;
                    } else if (pilihan == 99) {
                        OrderController.confirmAndPay();
                        validInput = true;
                    } else if (pilihan == 0) {
                        exit = true;
                        validInput = true;
                    } else {
                        System.out.println("Pilihan tidak ada");
                    }
                } catch (NumberFormatException e){
                    System.out.println("Input harus berupa angka!");
                }
            }
        }
        scanner.close();
    }
}
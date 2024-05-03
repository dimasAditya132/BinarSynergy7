package com.example.BinarFood.view;

import java.util.Scanner;

public class UserView {
    private Scanner scanner = new Scanner(System.in);

    public void displayMenu() {
        System.out.println("Pilih opsi: ");
        System.out.println("1. Tambah user");
        System.out.println("2. Update user");
        System.out.println("3. Hapus user");
        System.out.println("4. Kembali");
    }

    public String getUserChoice() {
        System.out.print("Pilih opsi: ");
        String choice = scanner.nextLine();
        System.out.println("--------------------------------------------------");
        return choice;
    }
}
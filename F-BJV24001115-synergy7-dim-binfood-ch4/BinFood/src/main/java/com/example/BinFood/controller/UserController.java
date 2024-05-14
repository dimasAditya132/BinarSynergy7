package com.example.BinFood.controller;

import com.example.BinFood.model.User;
import com.example.BinFood.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    private HomeController homeController;
    private final Scanner scanner = new Scanner(System.in);

    @Autowired
    public void setHomeController(@Lazy HomeController homeController) {
        this.homeController = homeController;
    }

    public void userMenu() {
        System.out.println();
        System.out.println("BinarFud - User Menu");
        System.out.println("Silahkan pilih tujuan: ");
        System.out.println("1. Tambah user");
        System.out.println("2. Update user");
        System.out.println("0. Kembali");
        int userInput = scanner.nextInt();
        switch (userInput) {
            case 1:
                scanner.nextLine();
                this.addUser();
                break;
            case 2:
                scanner.nextLine();
                this.updateUser();
                break;
            case 0:
                scanner.nextLine();
                homeController.mainMenu();
                break;
        }

    }

    public void addUser() {
        System.out.println();
        System.out.println("Masukkan username, password, dan e-mail yang baru");
        System.out.print("Username baru: ");
        String usernameInput = scanner.nextLine();
        System.out.print("Password baru: ");
        String passwordInput = scanner.nextLine();
        System.out.print("E-mail baru: ");
        String emailInput = scanner.nextLine();
        userService.addUser(User.builder()
                .username(usernameInput)
                .emailAddress(emailInput)
                .password(passwordInput)
                .build());
        log.info("Succesfully added user to database");
        this.userMenu();
    }

    public void updateUser() {
        System.out.println();
        System.out.println("Pilih peng-update-an: ");
        System.out.println("1. Update username baru");
        System.out.println("2. Update password baru");
        System.out.println("3. Hapus user");
        System.out.println("0. Kembali");
        int userInput = scanner.nextInt();
        switch (userInput) {
            case 1:
                System.out.println();
                scanner.nextLine();
                System.out.println("Silahkan masukkan username yang akan diubah usernamenya: ");
                String usernameInput1 = scanner.nextLine();
                System.out.println("Silahkan masukkan username baru yang diinginkan: ");
                String usernameInput2 = scanner.nextLine();
                userService.updateUsername(usernameInput2, usernameInput1);
                log.info("Succesfully updated user to database");
                this.updateUser();
                break;
            case 2:
                System.out.println();
                scanner.nextLine();
                System.out.println("Silahkan masukkan username yang akan diubah passwordnya: ");
                String usernameInput3 = scanner.nextLine();
                System.out.println("Silahkan masukkan password baru yang diinginkan: ");
                String passwordInput1 = scanner.nextLine();
                userService.updatePassword(passwordInput1, usernameInput3);
                log.info("Succesfully updated user to database");
                this.updateUser();
                break;
            case 3:
                System.out.println();
                scanner.nextLine();
                System.out.println("Silahkan masukkan username yang akan dihapus: ");
                String usernameInput = scanner.nextLine();
                userService.deleteUser(usernameInput);
                log.info("Succesfully deleted user from database");
                this.updateUser();
                break;
            case 0:
                System.out.println();
                scanner.nextLine();
                this.userMenu();
                break;
        }
    }


}

package com.example.BinarFood.controller;

import com.example.BinarFood.model.User;
import com.example.BinarFood.service.UserService;
import com.example.BinarFood.view.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    private UserView userView = new UserView();

    private Scanner scanner =new Scanner(System.in);
    public void userMenu() {
        while (true) {
            userView.displayMenu();
            String option = userView.getUserChoice();

            switch (option) {
                case "1":
                    addUser();
                    break;
                case "2":
                    updateUser();
                    break;
                case "3":
                    deleteUser();
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Opsi tidak valid. Silakan masukkan angka 1-4.");
            }
        }
    }


    public void addUser(){
        boolean isUserAdded = userService.addUser();
        if (isUserAdded){
            System.out.println("Data User berhasil ditambahkan");
        } else {
            System.out.println("Data User gagal ditambahkan");
        }
    }

    public void updateUser() {
        try {
            User updatedUser = userService.updateUser();
            if (updatedUser != null) {
                System.out.println("Data User " + updatedUser.getUsername() + " telah diperbarui.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteUser(){
        User deleteUSer = userService.deleteUser();
        System.out.println("User "+deleteUSer.getUsername()+" berhasil dihapus");
    }
}

package com.example.BinFood.controller;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
@Slf4j
public class HomeController {
    private Scanner scanner = new Scanner(System.in);

    private MerchantController merchantController;
    private ProductController productController;
    private UserController userController;
    private OrderController orderController;

    @Autowired
    public void setMerchantController(@Lazy MerchantController merchantController) {
        this.merchantController = merchantController;
    }

    @Autowired
    public void setProductController(@Lazy ProductController productController) {
        this.productController = productController;
    }

    @Autowired
    public void setUserController(@Lazy UserController userController) {
        this.userController = userController;
    }

    @Autowired
    public void setOrderController(@Lazy OrderController orderController) {
        this.orderController = orderController;
    }

    @PostConstruct
    public void init() {
//        this.mainMenu();
    }

    public void mainMenu() {
        System.out.println("BinarFud");
        System.out.println("Silahkan pilih tujuan: ");
        System.out.println("1. User");
        System.out.println("2. Merchant");
        System.out.println("3. Product");
        System.out.println("4. Order");
        System.out.println("0. Exit");
        System.out.print("=> ");
        int userInput = scanner.nextInt();
        switch (userInput) {
            case 1:
                scanner.nextLine();
                userController.userMenu();
                break;
            case 2:
                scanner.nextLine();
                merchantController.merchantMenu();
                break;
            case 3:
                scanner.nextLine();
                productController.productMenu();
                break;
            case 4:
                scanner.nextLine();
                orderController.orderMenu();
                break;
            case 0:
                System.out.println("Exiting...");
                System.exit(0);
        }
    }
}

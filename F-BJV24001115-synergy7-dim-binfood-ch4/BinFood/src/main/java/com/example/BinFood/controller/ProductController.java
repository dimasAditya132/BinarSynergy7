package com.example.BinFood.controller;

import com.example.BinFood.service.MerchantService;
import com.example.BinFood.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class ProductController {
    private final Scanner scanner = new Scanner(System.in);
    @Autowired
    private ProductService productService;
    @Autowired
    private MerchantService merchantService;
    private HomeController homeController;

    @Autowired
    public void setHomeController(@Lazy HomeController homeController) {
        this.homeController = homeController;
    }

    public void productMenu() {
        System.out.println();
        System.out.println("BinarFud - Product Menu");
        System.out.println("1. Tambah produk baru");
        System.out.println("2. Update detail produk");
        System.out.println("3. Hapus produk");
        System.out.println("4. Tampilkan produk");
        System.out.println("0. Kembali");
        int userInput = scanner.nextInt();
        switch (userInput) {
            case 1:
                scanner.nextLine();
                this.addProduct();
                break;
            case 2:
                scanner.nextLine();
                this.updateProduct();
                break;
            case 3:
                scanner.nextLine();
                this.deleteProduct();
                break;
            case 4:
                scanner.nextLine();
                this.showProduct();
                break;
            case 0:
                scanner.nextLine();
                homeController.mainMenu();
                break;
        }
    }

    public void addProduct() {
        System.out.println();
        merchantService.openNowMerchant();
        System.out.println("Pilih salah satu dari merchant tersebut");
        System.out.print("=> ");
        int userInput = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nama produk yang ingin ditambahkan: ");
        String newProdInput = scanner.nextLine();
        System.out.print("Harga produk yang ingin ditambahkan: ");
        double newProdPriceInput = scanner.nextDouble();
        productService.addProductToDB(productService.productBuilder(newProdInput, newProdPriceInput, merchantService.listOfMerchant().get(userInput).getMerchantName()));
        System.out.println("Berhasil ditambahkan");
        this.productMenu();
    }

    public void updateProduct() {
        System.out.println();
        merchantService.openNowMerchant();
        System.out.println("Pilih salah satu dari merchant tersebut");
        System.out.print("=> ");
        int userInput = scanner.nextInt();
        scanner.nextLine();
        String chosenMerchant = merchantService.listOfMerchant().get(userInput).getMerchantName();
        System.out.println("Berikut ini adalah produk-produk dari " + chosenMerchant);
        productService.viewListOfProduct(productService.ListOfAvailableProduct(chosenMerchant, 0));
        System.out.println("Pilih salah satu produk yang akan di-update");
        System.out.print("=> ");
        int userInput1 = scanner.nextInt();
        scanner.nextLine();
        String oldProdName = productService.ListOfAvailableProduct(chosenMerchant, 0).get(userInput1).getProductName();
        double oldProdPrice = productService.ListOfAvailableProduct(chosenMerchant, 0).get(userInput1).getProductPrice();
        System.out.print("Input nama baru produk tersebut ('-' jika tidak ada): ");
        String newProdName = scanner.nextLine();
        if (newProdName.equalsIgnoreCase("-")) {
            newProdName = oldProdName;
        }
        System.out.print("Input nama harga baru produk tersebut ('0' jika tidak ada): ");
        double newProdPrice = scanner.nextDouble();
        scanner.nextLine();
        if (newProdPrice == 0) {
            newProdPrice = oldProdPrice;
        }
        productService.updateProductName(chosenMerchant, oldProdName, newProdName);
        productService.updateProductPrice(chosenMerchant, newProdName, newProdPrice);
        System.out.println("Sukses Meng-update produk");
        this.productMenu();
    }

    public void deleteProduct() {
        System.out.println();
        merchantService.openNowMerchant();
        System.out.println("Pilih salah satu dari merchant tersebut");
        System.out.print("=> ");
        int userInput = scanner.nextInt();
        scanner.nextLine();
        String chosenMerchant = merchantService.listOfMerchant().get(userInput).getMerchantName();
        System.out.println("Berikut ini adalah produk-produk dari " + chosenMerchant);
        productService.viewListOfProduct(productService.ListOfAvailableProduct(chosenMerchant, 0));
        System.out.println("Pilih salah satu produk yang akan di-delete");
        System.out.print("=> ");
        int userInput1 = scanner.nextInt();
        scanner.nextLine();
        String prodName = productService.ListOfAvailableProduct(chosenMerchant, 0).get(userInput1).getProductName();
        productService.removeProductOf(prodName, chosenMerchant);
        System.out.println("Produk " + prodName + " sukses dihapus");
        this.productMenu();
    }

    public void showProduct() {
        System.out.println();
        merchantService.openNowMerchant();
        System.out.println("Pilih salah satu dari merchant tersebut");
        System.out.print("=> ");
        int userInput = scanner.nextInt();
        scanner.nextLine();
        String chosenMerchant = merchantService.listOfMerchant().get(userInput).getMerchantName();
        System.out.println("Berikut ini adalah produk-produk dari " + chosenMerchant);
        productService.viewListOfProduct(productService.ListOfAvailableProduct(chosenMerchant, 0));
        System.out.println();
        this.productMenu();
    }
}

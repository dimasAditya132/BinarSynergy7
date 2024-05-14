package com.example.BinFood.controller;

import com.example.BinFood.model.Merchant;
import com.example.BinFood.service.MerchantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
@Slf4j
public class MerchantController {
    @Autowired
    private MerchantService merchantService;
    private HomeController homeController;
    private final Scanner scanner = new Scanner(System.in);

    @Autowired
    public void setHomeController(@Lazy HomeController homeController) {
        this.homeController = homeController;
    }

    public void merchantMenu() {
        System.out.println();
        System.out.println("BinarFud - Merchant Menu");
        System.out.println("Silahkan pilih tujuan: ");
        System.out.println("1. Tambah Merchant");
        System.out.println("2. Update Status Merchant");
        System.out.println("3. Tampilkan merchant yang sedang buka");
        System.out.println("0. Kembali");
        int userInput = scanner.nextInt();
        switch (userInput) {
            case 1:
                scanner.nextLine();
                this.addMerchant();
                break;
            case 2:
                scanner.nextLine();
                this.updateMerchant();
                break;
            case 3:
                scanner.nextLine();
                this.showMerchant();
                break;
            case 0:
                scanner.nextLine();
                homeController.mainMenu();
                break;
        }
    }

    public void addMerchant() {
        System.out.println();
        System.out.println("Silahkan tambahkan nama dan status merchant yang akan dibuka: ");
        System.out.print("Nama merchant: ");
        String merchantNameInput = scanner.nextLine();
        System.out.print("Lokasi merchant: ");
        String merchantLocInput = scanner.nextLine();
        System.out.print("Status (Y/N) merchant: ");
        boolean merchantStatInput = scanner.hasNext("Y");
        merchantService.addMerchant(Merchant.builder()
                .merchantName(merchantNameInput)
                .merchantLocation(merchantLocInput)
                .status(merchantStatInput)
                .build());
        log.info("Succesfully added merchant to database");
        scanner.nextLine();
        this.merchantMenu();
    }

    public void updateMerchant() {
        System.out.println();
        System.out.println();
        System.out.println("Pilih peng-update-an: ");
        System.out.println("1. Update status merchant");
        System.out.println("2. Hapus merchant");
        System.out.println("0. Kembali");
        System.out.println();
        int userInput = scanner.nextInt();
        switch (userInput) {
            case 1:
                scanner.nextLine();
                System.out.println();
                System.out.println("Masukkan nama merchant: ");
                System.out.print("Nama merchant: ");
                String merchantNameInput = scanner.nextLine();
                System.out.print("Aktifkan (Y/N): ");
                boolean merchantStatInput = scanner.hasNext("Y");
                merchantService.changeMerchantStatus(merchantNameInput, merchantStatInput);
                scanner.nextLine();
                log.info("Succesfully updated merchant to database");
                this.updateMerchant();
                break;
            case 2:
                System.out.println();
                scanner.nextLine();
                System.out.println("Masukkan nama merchant: ");
                System.out.print("Nama merchant: ");
                String merchantNameInput1 = scanner.nextLine();
                merchantService.deleteMerchant(merchantNameInput1);
                log.info("Succesfully deleted merchant from database");
                this.updateMerchant();
                break;
            case 0:
                System.out.println();
                scanner.nextLine();
                this.merchantMenu();
                break;
        }
    }

    public void showMerchant() {
        System.out.println();
        System.out.println("Berikut ini ada list merchant yang sedang aktif: ");
        System.out.println("Nama merchant \t|\t Lokasi Merchant");
        merchantService.openNowMerchant();
        this.merchantMenu();
    }

}

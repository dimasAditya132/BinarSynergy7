package com.example.BinFood.controller;

import com.example.BinFood.model.Merchant;
import com.example.BinFood.service.MerchantService;
import com.example.BinFood.view.MerchantView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Scanner;

@Controller
public class MerchantController {
    @Autowired
    private MerchantService merchantService;

    @Autowired
    private MerchantView merchantView;

    private Scanner scanner = new Scanner(System.in);

    public void mainMenuMerchant() {
        while (true) {
            System.out.println("1. Tambah Merchant");
            System.out.println("2. Update Status Merchant");
            System.out.println("3. Lihat Merchant yang Buka");
            System.out.println("4. Kembali ke Menu Utama");
            System.out.print("Pilih opsi: ");
            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    addMerchant();
                    break;
                case "2":
                    updateMerchantStatus();
                    break;
                case "3":
                    displayOpenMerchants();
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Opsi tidak valid. Silakan masukkan angka 1-5.");
            }
        }
    }

    public void displayAllMerchants() {
        List<Merchant> allMerchants = merchantService.getAllMerchants();
        merchantView.displayAllMerchants(allMerchants);
    }

    public void addMerchant() {
        boolean isMerchantAdded = merchantService.addMerchant();
        if (isMerchantAdded) {
            System.out.println("Data Merchant berhasil ditambahkan");
        } else {
            System.out.println("Data Merchant gagal ditambahkan");
        }
    }

    public void updateMerchantStatus() {
        System.out.print("Masukkan nama merchant yang ingin diupdate statusnya: ");
        String name = scanner.nextLine();

        String isOpenInput;
        boolean isOpen;
        while (true) {
            System.out.print("Apakah merchant ini buka? (Y/N)");
            isOpenInput = scanner.nextLine();
            if (isOpenInput.equalsIgnoreCase("Y") || isOpenInput.equalsIgnoreCase("N")) {
                isOpen = isOpenInput.equalsIgnoreCase("Y");
                break;
            } else {
                System.out.println("Input tidak valid. Silakan masukkan Y untuk buka atau N untuk tutup.");
            }
        }

        Merchant updatedMerchant = merchantService.updateMerchantStatus(name, isOpen);
        if (updatedMerchant != null) {
            System.out.println("Status merchant berhasil diperbarui");
        } else {
            System.out.println("Gagal memperbarui status merchant");
        }
    }

    public void displayOpenMerchants() {
        List<Merchant> openMerchants = merchantService.getOpenMerchants();
        merchantView.displayMerchantOpen(openMerchants);
    }

}

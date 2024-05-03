package com.example.BinarFood.view;

import com.example.BinarFood.model.Merchant;
import com.example.BinarFood.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class MerchantView {
    @Autowired
    private MerchantService merchantService;

    private Scanner scanner = new Scanner(System.in);

    public void displayAllMerchants(List<Merchant> merchants) {
        System.out.println("Merchant BinarFood");
        if (merchants.isEmpty()) {
            System.out.println("Tidak ada merchant.");
        } else {
            System.out.println(String.format("%-30s %-30s %-10s", "Nama Merchant", "Alamat Merchant", "Status"));
            for (Merchant merchant : merchants) {
                System.out.println(String.format("%-30s %-30s %-10s", merchant.getMerchantName(), merchant.getMerchantLocation(), merchant.getOpen() ? "Buka" : "Tutup"));
            }
        }
    }

    public void displayMerchantOpen(List<Merchant> merchants){
        if (merchants.isEmpty()){
            System.out.println("Tidak ada merchant yang buka");
        } else {
            System.out.println("Merchant yang buka: ");
            for (Merchant merchant : merchants){
                System.out.println("Nama : "+merchant.getMerchantName());
                System.out.println("Alamat : "+merchant.getMerchantLocation());
                System.out.println("====================================");
            }
        }
    }
}
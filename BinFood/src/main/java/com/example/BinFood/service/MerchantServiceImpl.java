package com.example.BinFood.service;

import com.example.BinFood.model.Merchant;
import com.example.BinFood.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    MerchantRepository merchantRepository;

    private Scanner scanner = new Scanner(System.in);
    @Override
    public boolean addMerchant() {

        System.out.print("Masukkan nama merchant: ");
        String name = scanner.nextLine();

        System.out.print("Masukkan alamat merchant: ");
        String address = scanner.nextLine();

        System.out.print("Apakah merchant ini buka? (Y/N)");
        String isOpenInput = scanner.nextLine();
        boolean isOpen = isOpenInput.equalsIgnoreCase("Y");


        Merchant merchant = new Merchant();
        merchant.setMerchantName(name);
        merchant.setMerchantLocation(address);
        merchant.setOpen(isOpen);

        Merchant savedMerchant = merchantRepository.save(merchant);

        return savedMerchant != null;
    }

    @Override
    public Merchant addMerchant(Merchant merchant) {
        return merchantRepository.save(merchant);
    }

    @Override
    public Merchant updateMerchantStatus(String name, boolean isOpen) {
        Merchant merchant = merchantRepository.findMerchantByMerchantName(name)
                .orElseThrow(() -> new IllegalArgumentException("Merchant tidak ditemukan"));

        merchant.setOpen(isOpen);
        Merchant updatedMerchant = merchantRepository.save(merchant);

        return updatedMerchant;
    }
    @Override
    public List<Merchant> getOpenMerchants() {
        return merchantRepository.findByOpenTrue();
    }

    @Override
    public List<Merchant> getAllMerchants() {
        return null;
    }
    @Override
    public Merchant getMerchantById(UUID id) {
        return merchantRepository.findById(id).orElse(null);
    }
}

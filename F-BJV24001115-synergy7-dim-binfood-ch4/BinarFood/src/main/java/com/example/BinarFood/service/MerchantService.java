package com.example.BinarFood.service;

import com.example.BinarFood.model.Merchant;
import java.util.List;
import java.util.UUID;

public interface MerchantService {
    boolean addMerchant();
    Merchant addMerchant(Merchant merchant);
    Merchant updateMerchantStatus(String name, boolean isOpen);
    List<Merchant> getOpenMerchants();
    List<Merchant> getAllMerchants();
}
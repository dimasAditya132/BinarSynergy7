package com.example.BinFood.service;

import com.example.BinFood.model.Merchant;
import java.util.List;
import java.util.UUID;

public interface MerchantService {
    boolean addMerchant();
    Merchant addMerchant(Merchant merchant);
    Merchant updateMerchantStatus(String name, boolean isOpen);
    List<Merchant> getOpenMerchants();
    List<Merchant> getAllMerchants();
    Merchant getMerchantById(UUID id);
}
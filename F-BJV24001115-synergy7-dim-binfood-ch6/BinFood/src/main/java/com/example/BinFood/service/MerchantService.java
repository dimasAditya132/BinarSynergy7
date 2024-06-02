package com.example.BinFood.service;

import com.example.BinFood.model.Merchant;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MerchantService {
    Merchant insertMerchant(Merchant merchant);

    Merchant getMerchantByName(String merchantName);

    Merchant getMerchantById(String id);

    Merchant updateMerchant(Merchant merchant);

    Merchant updateMerchant(String id, Merchant merchant);

    List<Merchant> getAllMerchant();

    List<Merchant> getAllMerchantFilter(boolean isOpen);

    void hardDeleteMerchant(Merchant merchant);
}
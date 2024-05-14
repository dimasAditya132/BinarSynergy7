package com.example.BinFood.service;

import com.example.BinFood.model.Merchant;
import com.example.BinFood.model.response.MerchantResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MerchantService {
    void addMerchant(Merchant merchant);
    void changeMerchantStatus(String merchantName, boolean tof);

    void openNowMerchant();

    void deleteMerchant(String merchantName);

    List<MerchantResponse> listOfMerchant();
}
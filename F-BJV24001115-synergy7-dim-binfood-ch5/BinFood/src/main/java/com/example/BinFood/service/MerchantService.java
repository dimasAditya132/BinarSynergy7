package com.example.BinFood.service;

import com.example.BinFood.model.Merchant;
import com.example.BinFood.model.response.MerchantResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MerchantService {
    boolean addMerchant(Merchant merchant);

    boolean changeMerchantStatus(String merchantName, boolean tof);

    boolean deleteMerchant(String merchantName);

    List<MerchantResponse> listOfMerchant();

    boolean merchantExist(String merchantName);

    List<MerchantResponse> pageOfMerchant(int inputPage);
}
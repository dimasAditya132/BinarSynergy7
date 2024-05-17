package com.example.BinFood.service;


import com.example.BinFood.model.Merchant;
import com.example.BinFood.model.response.MerchantResponse;
import com.example.BinFood.repository.MerchantRepository;
import com.example.BinFood.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MerchantServiceImpl implements MerchantService {
    @Autowired
    private MerchantRepository merchantRepository;
    @Autowired
    private ProductRepository productRepository;


    @Override
    public boolean addMerchant(Merchant merchant) {
        if (merchant == null || merchant.getMerchantName() == null || merchant.getMerchantLocation() == null) {
            log.error("Failed to add merchant to the database: Merchant or required fields are null");
            return false;
        }

        try {
            log.info("Attempting to add merchant");
            merchantRepository.save(merchant);
            log.info("Successfully added {} to the database", merchant.getMerchantName());
            return true;
        } catch (Exception e) {
            log.error("Failed to add merchant to the database");
            log.info("Cause " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean changeMerchantStatus(String merchantName, boolean tof) {
        if (this.merchantExist(merchantName)) {
            log.info("Attempting to change merchant status");
            merchantRepository.queryUpdateMerchantStatus(tof, merchantName);
            log.info("Successfully changed merchant {} status", merchantName);
            return true;
        } else {
            log.error("Failed to change merchant status");
            return false;
        }
    }

    @Override
    public boolean deleteMerchant(String merchantName) {
        if (this.merchantExist(merchantName)) {
            log.warn("Deleting all product from merchant");
            productRepository.queryListOfProductFromMerch(merchantName).forEach(product -> productRepository.delete(product));
            log.info("Attempting to delete merchant");
            merchantRepository.queryDeleteMerchant(merchantName);
            log.info("Successfully deleted merchant {}", merchantName);
            return true;
        } else {
            log.error("Failed to delete merchant");
            return false;
        }
    }

    @Override
    public List<MerchantResponse> listOfMerchant() {
        return merchantRepository.queryActiveMerchant(PageRequest.of(0, 5)).stream().map(merchant -> MerchantResponse.builder().merchantName(merchant.getMerchantName()).merchantLocation(merchant.getMerchantLocation()).build()).collect(Collectors.toList());
    }

    @Override
    public boolean merchantExist(String merchantName) {
        try {
            Merchant merchant = merchantRepository.queryFindMerchantByName(merchantName);
            return (Objects.nonNull(merchant));
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<MerchantResponse> pageOfMerchant(int inputPage) {
        Page<Merchant> merchants = merchantRepository.queryPagedMerchantList(PageRequest.of(inputPage, 5));
        return merchants.stream().map(merchant -> MerchantResponse.builder().merchantName(merchant.getMerchantName()).merchantLocation(merchant.getMerchantLocation()).build()).collect(Collectors.toList());
    }
}

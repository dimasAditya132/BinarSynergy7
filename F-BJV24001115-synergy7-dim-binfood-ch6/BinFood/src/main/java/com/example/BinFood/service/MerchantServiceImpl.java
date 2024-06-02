package com.example.BinFood.service;


import com.example.BinFood.model.Merchant;
import com.example.BinFood.repository.MerchantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class MerchantServiceImpl implements MerchantService {
    @Autowired
    MerchantRepository merchantRepository;

    @Override
    public Merchant insertMerchant(Merchant merchant) {
        merchant = merchantRepository.save(merchant);
        log.info("Mechant data succesfully created");
        return merchant;
    }

    @Override
    public Merchant getMerchantByName(String merchantName) {
        Optional<Merchant> merchant = merchantRepository.findByMerchantName(merchantName);
        if (merchant.isEmpty()) {
            throw new RuntimeException("data with merchant_name \"" + merchantName + "\" not found");
        }
        return merchant.get();
    }

    @Override
    public Merchant getMerchantById(String id) {
        UUID uuid = UUID.fromString(id);
        Optional<Merchant> merchant = merchantRepository.findById(uuid);
        if (merchant.isEmpty()) {
            throw new RuntimeException("data with merchant_id \"" + id + "\" not found");
        }
        return merchant.get();
    }

    @Override
    public Merchant updateMerchant(Merchant merchant) {
        merchant = merchantRepository.save(merchant);
        log.debug("Update Success");
        return merchant;
    }

    @Override
    public Merchant updateMerchant(String merchantId, Merchant merchant) {
        Optional<Merchant> oldMerchantData = merchantRepository.findById(UUID.fromString(merchantId));
        if (oldMerchantData.isEmpty()) {
            throw new RuntimeException("Data doesn't exist, process cancelled");
        }

        Merchant merchantData = oldMerchantData.get();
        if (merchant.getMerchantName() != null) merchantData.setMerchantName(merchant.getMerchantName());
        if (merchant.getMerchantLocation() != null) merchantData.setMerchantLocation(merchant.getMerchantLocation());
        if (merchant.isOpen() != oldMerchantData.get().isOpen()) merchantData.setOpen(merchant.isOpen());
        merchantRepository.save(merchantData);
        return merchantData;
    }

    @Override
    public List<Merchant> getAllMerchant() {
        List<Merchant> merchantList = merchantRepository.findAll();
        if (merchantList.isEmpty()) {
            return Collections.emptyList();
        }
        return merchantList;
    }

    @Override
    public List<Merchant> getAllMerchantFilter(boolean isOpen) {
        if (isOpen) {
            return merchantRepository.findMerchantByOpenIsTrue();
        }
        return merchantRepository.findMerchantByOpenIsFalse();
    }

    @Override
    public void hardDeleteMerchant(Merchant merchant) {
        merchantRepository.delete(merchant);
        log.info("Data with merchant name \"" + merchant.getMerchantName() + "\" has been deleted");
    }
}
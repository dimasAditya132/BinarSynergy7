package com.example.BinFood.service;


import com.example.BinFood.model.Merchant;
import com.example.BinFood.model.response.MerchantResponse;
import com.example.BinFood.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class MerchantServiceImpl implements MerchantService {
    @Autowired
    private MerchantRepository merchantRepository;

    @Override
    public void addMerchant(Merchant merchant) {
        merchantRepository.save(Optional.ofNullable(merchant)
                .filter(val -> val.getMerchantName() != null && val.getMerchantLocation() != null)
                .orElse(Merchant.builder()
                        .id(UUID.randomUUID())
                        .build()));
        try {
            deleteMerchant(merchant.getMerchantName());
            System.out.println("Merchant berhasil ditambahkan!");
        } catch (Exception e) {
            System.out.println("Merchant berhasil ditambahkan!");
        }
    }

    @Override
    public void changeMerchantStatus(String merchantName, boolean tof) {
        merchantRepository.queryUpdateMerchantStatus(tof, merchantName);
    }

    @Override
    public void openNowMerchant() {
        List<MerchantResponse> merchantResponseList = this.listOfMerchant();
        AtomicInteger index = new AtomicInteger(0);
        merchantResponseList.forEach(val ->
                System.out.println(index.getAndIncrement()+". "+val.getMerchantName()+"\t|\t"+val.getMerchantLocation()));
    }

    @Override
    public void deleteMerchant(String merchantName) {
        merchantRepository.queryDeleteMerchant(merchantName);
    }

    @Override
    public List<MerchantResponse> listOfMerchant() {
        return merchantRepository.queryActiveMerchant(PageRequest.of(0,5))
                .stream()
                .map(merchant -> MerchantResponse
                        .builder()
                        .merchantName(merchant.getMerchantName())
                        .merchantLocation(merchant.getMerchantLocation())
                        .build())
                .collect(Collectors.toList());
    }
}

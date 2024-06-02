package com.example.BinFood.payload;

import com.example.BinFood.model.Merchant;
import lombok.Data;

import java.util.UUID;

@Data
public class ProductDTO {
    private UUID id;
    private String productName;
    private Double price;
    private UUID merchant;
    private String merchantName;

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant.getId();
        this.merchantName = merchant.getMerchantName();
    }
}

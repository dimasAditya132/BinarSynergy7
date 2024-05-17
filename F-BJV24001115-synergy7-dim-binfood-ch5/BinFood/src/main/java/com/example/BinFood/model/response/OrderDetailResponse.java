package com.example.BinFood.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailResponse {
    private String productName;
    private double productPrice;
    private double productFinalPrice;
    private int productQuantity;

    public double getProductFinalPrice() {
        return productFinalPrice;
    }
}

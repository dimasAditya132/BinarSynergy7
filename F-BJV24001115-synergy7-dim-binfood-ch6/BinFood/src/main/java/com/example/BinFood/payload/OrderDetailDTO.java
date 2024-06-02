package com.example.BinFood.payload;

import com.example.BinFood.model.Product;
import lombok.Data;

@Data
public class OrderDetailDTO {
    private String id;

    private String productId;
    private String productName;
    private int quantity;

    public void setProduct(Product product) {
        this.productId = product.getId().toString();
        this.productName = product.getProductName();
    }
}

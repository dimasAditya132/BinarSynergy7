package com.example.BinFood.service;

import com.example.BinFood.model.Product;
import com.example.BinFood.model.response.ProductResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    Product productBuilder(String productName, double productPrice, String merchantName);
    void addProductToDB(Product product);

    void updateProductName(String merchantName, String oldProductName, String newProductName);
    void updateProductPrice(String merchantName, String productName, double newProductPrice);

    void removeProductOf(String productName, String merchantName);

    List<ProductResponse> ListOfAvailableProduct(String merchantName, int page);
    void viewListOfProduct(List<ProductResponse> products);



}

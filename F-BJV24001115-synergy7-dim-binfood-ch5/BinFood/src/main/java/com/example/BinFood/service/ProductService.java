package com.example.BinFood.service;

import com.example.BinFood.model.Product;
import com.example.BinFood.model.response.ProductResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    Product productBuilder(String productName, double productPrice, String merchantName);

    boolean addProductToDB(Product product);

    boolean productExist(String merchantName, String productName);

    boolean updateProductName(String merchantName, String oldProductName, String newProductName);

    boolean updateProductPrice(String merchantName, String productName, double newProductPrice);

    boolean removeProductOf(String productName, String merchantName);

    List<ProductResponse> ListOfAvailableProduct(String merchantName, int page);

    ProductResponse oneProduct(String merchantName, String productName);


}

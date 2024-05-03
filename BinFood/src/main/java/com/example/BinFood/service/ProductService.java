package com.example.BinFood.service;

import com.example.BinFood.model.Product;

import java.util.UUID;

public interface ProductService {
    boolean addProduct();
    Product addProduct(Product product);
    String deleteProduct();
    Product updateProduct();

}

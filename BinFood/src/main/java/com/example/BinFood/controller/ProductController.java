package com.example.BinFood.controller;

import com.example.BinFood.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    public void addProduct(){
       boolean isProductAdded = productService.addProduct();
         if (isProductAdded){
              System.out.println("Produk berhasil ditambahkan");
         } else {
              System.out.println("Produk gagal ditambahkan");
         }
    }

    public void deleteProduct(){
        String message = productService.deleteProduct();
        System.out.println(message);
    }

    public void updateProduct(){
        try{
            productService.updateProduct();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}

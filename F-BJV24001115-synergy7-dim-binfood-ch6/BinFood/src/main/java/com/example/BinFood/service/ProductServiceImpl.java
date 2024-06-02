package com.example.BinFood.service;


import com.example.BinFood.model.Product;
import com.example.BinFood.repository.MerchantRepository;
import com.example.BinFood.repository.ProductRepository;
import jakarta.annotation.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product insertProduct(Product product) {
        product = productRepository.save(product);
        log.info("Product data successfully created");
        return product;
    }

    @Override
    public Product getProductById(String productId) {
        UUID uuid = UUID.fromString(productId);
        Optional<Product> product = productRepository.findById(uuid);
        if (product.isEmpty()) {
            throw new RuntimeException("data with product_id \"" + productId + "\" not found");
        }
        return product.get();
    }

    @Override
    public Product updateProduct(Product product, @Nullable String newProductName, @Nullable Double newPrice) {
        if (newProductName != null) {
            product.setProductName(newProductName);
        }
        if (newPrice != null) {
            product.setPrice(newPrice);
        }
        if ((newProductName == null) && (newPrice == null)) {
            throw new RuntimeException("No data to update");
        }
        product = productRepository.save(product);
        log.info("Product data successfully updated");
        return product;
    }

    @Override
    public void deleteProduct(Product product) {
        productRepository.delete(product);
        log.info("Data with product name \"" + product.getProductName() + "\" successfully deleted");
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            return Collections.emptyList();
        }
        return products;
    }

    @Override
    public List<Map<String, Object>> fetchProducts(boolean open) {
        return productRepository.fetchProductByMerchantStatus(open);
    }
}

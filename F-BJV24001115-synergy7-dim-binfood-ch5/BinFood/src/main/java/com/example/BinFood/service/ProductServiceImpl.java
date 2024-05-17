package com.example.BinFood.service;


import com.example.BinFood.model.Product;
import com.example.BinFood.model.response.ProductResponse;
import com.example.BinFood.repository.MerchantRepository;
import com.example.BinFood.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private MerchantRepository merchantRepository;

    @Override
    public Product productBuilder(String productName, double productPrice, String merchantName) {
        return Product.builder()
                .productName(productName)
                .productPrice(productPrice)
                .merchant(merchantRepository.queryFindMerchantByName(merchantName))
                .build();
    }

    @Override
    public boolean addProductToDB(Product product) {
        try {
            log.info("Adding product to Database");
            productRepository.save(Objects.requireNonNull(Optional.ofNullable(product)
                    .filter(val -> val.getProductName() != null && val.getProductPrice() != 0)
                    .orElse(null)));
            log.info("Successfully added product {} of merchant {}", product.getProductName(), product.getMerchant().getMerchantName());
            return true;
        } catch (Exception e) {
            log.error("Failed to add product to Database");
            return false;
        }
    }

    @Override
    public boolean productExist(String merchantName, String productName) {
        try {
            return Objects.nonNull(productRepository.queryOneFromMerchant(merchantName, productName));
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateProductName(String merchantName, String oldProductName, String newProductName) {
        if (productExist(merchantName, oldProductName)) {
            log.info("Submitting edit to database");
            productRepository.queryEditProductName(merchantName, oldProductName, newProductName);
            log.info("Successfully edited product name ({} -> {})", oldProductName, newProductName);
            return true;
        } else {
            log.error("Could not edit product {} name", oldProductName);
            return false;
        }
    }

    @Override
    public boolean updateProductPrice(String merchantName, String productName, double newProductPrice) {
        if (productExist(merchantName, productName)) {
            log.info("Submitting edit to database");
            productRepository.queryEditProductPrice(merchantName, productName, newProductPrice);
            log.info("Successfully edited product price");
            return true;
        } else {
            log.error("Could not edit product {} price", productName);
            return false;
        }
    }

    @Override
    public boolean removeProductOf(String productName, String merchantName) {
        if (productExist(merchantName, productName)) {
            log.info("Deleting product from database");
            productRepository.queryDeleteProduct(merchantName, productName);
            log.info("Successfully deleted product {}", productName);
            return true;
        } else {
            log.error("Could not delete product {}", productName);
            return false;
        }
    }

    @Override
    public List<ProductResponse> ListOfAvailableProduct(String merchantName, int page) {
        return productRepository.queryFromCertainMerchant(merchantName, PageRequest.of(page, 5))
                .stream()
                .map(product -> ProductResponse.builder()
                        .productName(product.getProductName())
                        .productPrice(product.getProductPrice())
                        .build())
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse oneProduct(String merchantName, String productName) {
        if (productExist(merchantName, productName)) {
            Product quedProduct = productRepository.queryOneFromMerchant(merchantName, productName);
            return ProductResponse.builder()
                    .productName(quedProduct.getProductName())
                    .productPrice(quedProduct.getProductPrice())
                    .build();
        } else return new ProductResponse();
    }
}

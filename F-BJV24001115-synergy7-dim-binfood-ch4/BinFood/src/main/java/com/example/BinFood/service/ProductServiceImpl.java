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
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.Objects;

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
    public void addProductToDB(Product product) {
        productRepository.save(Optional.ofNullable(product)
                .filter(val -> val.getProductName() != null && val.getProductPrice() != 0)
                .orElse(Product.builder()
                        .id(UUID.randomUUID())
                        .build()));
        try {
            productRepository.deleteById("DELETETHIS2");
        } catch (Exception e) {
            log.info("Succesfully added to database");
        }

    }

    @Override
    public void updateProductName(String merchantName, String oldProductName, String newProductName) {
        productRepository.queryEditProductName(merchantName, oldProductName, newProductName);
    }

    @Override
    public void updateProductPrice(String merchantName, String productName, double newProductPrice) {
        productRepository.queryEditProductPrice(merchantName, productName, newProductPrice);
    }

    @Override
    public void removeProductOf(String productName, String merchantName) {
        productRepository.queryDeleteProduct(merchantName, productName);
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
    public void viewListOfProduct(List<ProductResponse> products) {
        AtomicInteger index = new AtomicInteger(-1);
        products.forEach(prod -> {
            index.addAndGet(1);
            System.out.println(index + " . " + prod.getProductName() + "\t\t" + prod.getProductPrice());
        });

    }
}

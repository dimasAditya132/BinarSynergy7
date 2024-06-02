package com.example.BinFood.controller;

import com.example.BinFood.model.Product;
import com.example.BinFood.payload.ProductDTO;
import com.example.BinFood.payload.Response;
import jakarta.annotation.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
@Slf4j
public class ProductController {
    final ModelMapper modelMapper;
    final ProductUtil productUtil;
    final MerchantUtil merchantUtil;

    public ProductController(ModelMapper modelMapper, ProductUtil productUtil, MerchantUtil merchantUtil) {
        this.modelMapper = modelMapper;
        this.productUtil = productUtil;
        this.merchantUtil = merchantUtil;
    }

    @GetMapping()
    @Secured({"CUSTOMER", "MERCHANT"})
    public ResponseEntity<Response> getAllProducts(
            @RequestParam("id") @Nullable String merchantId,
            @RequestParam("open") @Nullable Boolean isOpen
    ) {
        if (merchantId != null) {
            return ResponseEntity.ok(getProductsByMerchant(merchantId));
        } else {
            return ResponseEntity.ok(getProductsByMerchantStatus(isOpen));
        }
    }

    private Response getProductsByMerchant(String merchantId) {
        List<Product> productList;
        try {
            productList = merchantUtil.getMerchantDetail(merchantId).getProductList();
            List<ProductDTO> productDTOList = productList.stream()
                    .map(product -> modelMapper.map(product, ProductDTO.class))
                    .toList();
            return new Response.Success(productDTOList);
        } catch (RuntimeException e) {
            return new Response.Error("Merchant not found");
        }
    }

    private Response getProductsByMerchantStatus(@Nullable Boolean merchantIsOpen) {
        List<Product> productList = productUtil.getAllProducts(merchantIsOpen);

        List<ProductDTO> productDTOList = productList.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .toList();
        if (!productList.isEmpty()) {
            return new Response.Success(productDTOList);
        } else {
            return new Response.Error("No product found");
        }
    }

    @PostMapping("{merchantId}")
    public ResponseEntity<Response> addProduct(@PathVariable("merchantId") String merchantId, @RequestBody ProductDTO productDTO) {
        Product product;
        if ((productDTO.getProductName() != null) && (productDTO.getPrice() != null)) {
            try {
                product = productUtil.createUtil(productDTO.getProductName(), productDTO.getPrice(), merchantId);
                productDTO = modelMapper.map(product, ProductDTO.class);
                return ResponseEntity.ok(new Response.Success(productDTO));
            } catch (Exception e) {
                return new ResponseEntity<>(new Response.Error(e.getLocalizedMessage()), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(new Response.Error("Invalid input"), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Response> deleteProduct(@PathVariable("id") String id) {
        try {
            productUtil.deleteProduct(id);
            return ResponseEntity.ok(new Response.Success("Product deleted successfully"));
        } catch (RuntimeException e) {
            return new ResponseEntity<>(new Response.Error(e.getLocalizedMessage()), HttpStatus.OK);
        }
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('MERCHANT')")
    public ResponseEntity<Response> update(@PathVariable("id") String id, @RequestBody ProductDTO productDTO) {
        Product product;
        try {
            product = modelMapper.map(productDTO, Product.class);
            product = productUtil.editProduct(id, product.getProductName(), product.getPrice());
            return ResponseEntity.ok(new Response.Success(modelMapper.map(product, ProductDTO.class)));
        } catch (RuntimeException e) {
            return new ResponseEntity<>(new Response.Error(e.getLocalizedMessage()), HttpStatus.OK);
        }
    }
}

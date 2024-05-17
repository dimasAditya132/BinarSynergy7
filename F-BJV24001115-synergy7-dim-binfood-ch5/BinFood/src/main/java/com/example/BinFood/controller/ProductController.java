package com.example.BinFood.controller;

import com.example.BinFood.model.response.ErrorResponse;
import com.example.BinFood.model.response.ProductResponse;
import com.example.BinFood.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping(value = "add")
    public ResponseEntity<ErrorResponse<Object>> requestAddProduct(@RequestParam("merchant") String merchantName,
                                                                   @RequestBody ProductResponse productResponse) {
        if (productService.addProductToDB(productService.productBuilder(productResponse.getProductName(),
                productResponse.getProductPrice(),
                merchantName))) {
            return new ResponseEntity<>(ErrorResponse.builder()
                    .entity(productResponse)
                    .errorMessage("Successfully added product " + productResponse.getProductName() + " to " + merchantName)
                    .errorCode(HttpStatus.OK.value())
                    .build(),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ErrorResponse.builder()
                    .errorMessage("Failed to add product " + productResponse + " to " + merchantName)
                    .errorCode(HttpStatus.NOT_FOUND.value())
                    .build(), HttpStatus.NOT_FOUND);
        }
    }

    /*@PutMapping(value = "edit-name/{oldname}")
    public ResponseEntity<ErrorResponse<Object>> requestUpdateProductName(@PathVariable("oldname") String prodOldName,
                                                                          @RequestBody ProductResponse productResponse,
                                                                          @RequestParam("merchant") String merchantName) {
        if (productService.updateProductName(merchantName,
                prodOldName,
                productResponse.getProductName())) {
            return new ResponseEntity<>(ErrorResponse.builder()
                    .entity(productResponse)
                    .errorMessage("Successfully updated product name from " + prodOldName + " to " + productResponse.getProductName())
                    .errorCode(HttpStatus.OK.value())
                    .build(),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ErrorResponse.builder()
                    .errorMessage("Failed to update product name from " + prodOldName + " to " + productResponse.getProductName())
                    .errorCode(HttpStatus.NOT_FOUND.value())
                    .build(), HttpStatus.NOT_FOUND);
        }
    }*/
    @PutMapping("/edit-name/{productName}")
    public ResponseEntity<String> editProductName(@PathVariable String productName,
                                                  @RequestParam String newProductName,
                                                  @RequestParam String merchant) {
        try {
            productService.updateProductName(merchant, productName, newProductName);
            return new ResponseEntity<>("Product name updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update product name", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = " edit-price/{productName}")
    public ResponseEntity<ErrorResponse<Object>> requestUpdateProductPrice(@PathVariable("productName") String productName,
                                                                           @RequestBody ProductResponse productResponse,
                                                                           @RequestParam("merchant") String merchantName) {
        if (productService.updateProductPrice(merchantName,
                productName, productResponse.getProductPrice())) {
            return new ResponseEntity<>(ErrorResponse.builder()
                    .entity(productResponse)
                    .errorMessage("Successfully updated product " + productName + "price")
                    .errorCode(HttpStatus.OK.value())
                    .build(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ErrorResponse.builder()
                    .errorMessage("Failed to edit product " + productName + "to" + productResponse + " from " + merchantName)
                    .errorCode(HttpStatus.NOT_FOUND.value())
                    .build(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "delete/{productName}")
    public ResponseEntity<ErrorResponse<Object>> requestDeleteProduct(@RequestParam("merchant") String merchantName,
                                                                      @PathVariable("productName") String productName) {
        if (productService.removeProductOf(productName, merchantName)) {
            return new ResponseEntity<>(ErrorResponse.builder()
                    .errorMessage("Successfully deleted product " + productName)
                    .errorCode(HttpStatus.OK.value())
                    .build(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ErrorResponse.builder()
                    .errorMessage("Failed to delete " + productName)
                    .errorCode(HttpStatus.NOT_FOUND.value())
                    .build(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "get-product/{merchant}", produces = "application/json")
    public List<ProductResponse> requestGetProduct(@PathVariable("merchant") String merchantName,
                                                   @RequestParam("page") int page) {
        return productService.ListOfAvailableProduct(merchantName, page);
    }
}

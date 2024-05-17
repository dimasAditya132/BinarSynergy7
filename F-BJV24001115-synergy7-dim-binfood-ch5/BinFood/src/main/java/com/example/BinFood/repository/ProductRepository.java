package com.example.BinFood.repository;

import com.example.BinFood.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE product p " +
            "SET product_name = :new " +
            "WHERE p.product_name = :old " +
            "AND p.merchant_id = (SELECT m.id FROM merchant m WHERE m.merchant_name = :mname)")
    void queryEditProductName(@Param("mname") String merchantName, @Param("old") String oldProduct, @Param("new") String newProductName);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE product p " +
            "SET product_price = :new " +
            "WHERE p.product_name = :pname " +
            "AND p.merchant_id = (SELECT m.id FROM merchant m WHERE m.merchant_name = :mname)")
    void queryEditProductPrice(@Param("mname") String merchantName, @Param("pname") String productName, @Param("new") double newPrice);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "DELETE FROM product pr where pr.product_name = :pname " +
            "and pr.merchant_id = (select m.id from merchant m where m.merchant_name = :mname)")
    void queryDeleteProduct(@Param("pname") String productName, @Param("mname") String merchantName);

    @Query(nativeQuery = true, value = "SELECT product.*, merchant.merchant_name, merchant.merchant_location FROM product p " +
            "JOIN merchant m ON m.id = p.merchant_id " +
            "WHERE m.merchant_name = :mname")
    List<Product> queryFromCertainMerchant(@Param("mname") String name, Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT * FROM product p " +
            "WHERE p.product_name = :pname " +
            "AND p.merchant_id = (SELECT m.id FROM merchant m WHERE m.merchant_name = :mname)")
    Product queryOneFromMerchant(@Param("pname") String productName, @Param("mname") String merchantName);

    @Query(nativeQuery = true,value  = "SELECT * FROM product JOIN merchant ON merchant.id = product.merchant_id WHERE merchant.merchant_name = :merchantName")
    List<Product> queryListOfProductFromMerch(@Param("merchantName") String merchantName);

    @Query(nativeQuery = true, value = " SELECT * FROM product " +
            "JOIN merchant on merchant.id = product.merchant_id " +
            "WHERE merchant.merchant_name = :mname AND product.product_name = :pname")
    Product queryProductFromMerchant(@Param("mname") String merchantName, @Param("pname") String productName);
}

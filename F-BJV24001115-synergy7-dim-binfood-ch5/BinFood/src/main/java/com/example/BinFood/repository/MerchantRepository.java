package com.example.BinFood.repository;

import com.example.BinFood.model.Merchant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, String> {
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE merchant m set status = :boolval where m.merchant_name = :mname")
    void queryUpdateMerchantStatus(@Param("boolval") boolean status, @Param("mname") String mname);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "DELETE FROM merchants m WHERE m.merchant_name = :merchant_name")
    void queryDeleteMerchant(@Param("merchant_name") String merchantName);

    @Query(nativeQuery = true, value = "SELECT * FROM merchant as m where m.status = true")
    List<Merchant> queryActiveMerchant(PageRequest pageable);

    @Query(nativeQuery = true, value = "SELECT * FROM merchant m where m.merchant_name = :mname")
    Merchant queryFindMerchantByName(@Param("mname") String mname);

    @Query(nativeQuery = true, value = "SELECT * FROM merchant AS m where m.status = true")
    Page<Merchant> queryPagedMerchantList(Pageable pageable);

    default void deleteMerchant(String merchantName) {
        Merchant merchant = queryFindMerchantByName(merchantName);
        if (merchant != null) {
            delete(merchant);
        }
    }
}
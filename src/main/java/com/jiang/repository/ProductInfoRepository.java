package com.jiang.repository;

import com.jiang.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @program: purchase-sale-stock
 * @description:
 * @author: lvjx
 * @create: 2020-04-20 14:51
 **/
public interface ProductInfoRepository extends JpaRepository<ProductInfo,Long> {

    @Query(value = "update product_info count = count + :count where id = :id",nativeQuery = true)
    void updateCount(@Param("id") Long productInfoId,@Param("count") Long count);
}

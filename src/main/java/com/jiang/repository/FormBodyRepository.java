package com.jiang.repository;

import com.jiang.entity.FormBody;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @program: purchase-sale-stock
 * @description:
 * @author: lvjx
 * @create: 2020-04-20 15:48
 **/
public interface FormBodyRepository extends JpaRepository<FormBody,Long> {
    List<FormBody> findByHeadId(@Param("headId") Long headId);
}

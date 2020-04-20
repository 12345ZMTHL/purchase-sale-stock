package com.jiang.repository;

import com.jiang.entity.FormHead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @program: purchase-sale-stock
 * @description:
 * @author: lvjx
 * @create: 2020-04-20 15:28
 **/
public interface FormHeadRepository extends JpaRepository<FormHead,Long> {

}

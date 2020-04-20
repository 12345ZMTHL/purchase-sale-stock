package com.jiang.entity;

import lombok.CustomLog;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @program: purchase-sale-stock
 * @description: 商品详情
 * @author: lvjx
 * @create: 2020-04-20 11:54
 **/
@Entity
@Table(name = "product_info")
@Data
public class ProductInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name",length = 50)
    private String name;

    @Column(name = "count")
    private Long count;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "update_date")
    private Date updateDate;
}

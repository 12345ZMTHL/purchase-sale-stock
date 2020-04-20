package com.jiang.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @program: purchase-sale-stock
 * @description: 单身信息（具体某个商品的入库出库数量）
 * @author: lvjx
 * @create: 2020-04-20 12:28
 **/
@Entity
@Table(name = "form_body")
@Data
public class FormBody {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "head_id")
    private Long headId;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "count")
    private Long count;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "update_date")
    private Date updateDate;
}

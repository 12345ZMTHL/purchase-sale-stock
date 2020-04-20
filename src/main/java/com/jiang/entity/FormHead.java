package com.jiang.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @program: purchase-sale-stock
 * @description: 单头（入库、出库）
 * @author: lvjx
 * @create: 2020-04-20 12:00
 **/
@Entity
@Data
@Table(name = "form_head")
public class FormHead {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "flag")
    private Boolean flag;//决定是出货单还是进货单

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "update_date")
    private Date updateDate;
}

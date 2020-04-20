package com.jiang.dto;

import lombok.Data;

/**
 * @program: purchase-sale-stock
 * @description: 保存单身数据，某个商品出进货数量
 * @author: lvjx
 * @create: 2020-04-20 17:34
 **/
@Data
public class ProductInfoCountDTO {
    private Long productId;
    private Long count;
}

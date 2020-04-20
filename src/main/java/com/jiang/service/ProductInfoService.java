package com.jiang.service;

import com.jiang.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * @program: purchase-sale-stock
 * @description:
 * @author: lvjx
 * @create: 2020-04-20 14:21
 **/
public interface ProductInfoService {
    Page<ProductInfo> listPage(ProductInfo vo, PageRequest pageable);

    Integer addProductInfo(ProductInfo vo);

    Integer updateProductInfo(ProductInfo vo);

    Integer deleteProductInfoById(Long id);
}

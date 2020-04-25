package com.jiang.service;

import com.jiang.entity.ProductInfo;
import com.jiang.vo.QueryVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * @program: purchase-sale-stock
 * @description:
 * @author: lvjx
 * @create: 2020-04-20 14:21
 **/
public interface ProductInfoService {
    Page<ProductInfo> listPage(QueryVO vo, PageRequest pageable);

    Integer addProductInfo(ProductInfo vo);

    Integer updateProductInfo(ProductInfo vo);

    Integer deleteProductInfoById(Long id);
}

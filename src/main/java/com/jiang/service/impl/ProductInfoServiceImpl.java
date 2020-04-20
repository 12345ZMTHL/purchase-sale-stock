package com.jiang.service.impl;

import com.jiang.entity.ProductInfo;
import com.jiang.entity.QProductInfo;
import com.jiang.repository.ProductInfoRepository;
import com.jiang.service.ProductInfoService;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: purchase-sale-stock
 * @description:
 * @author: lvjx
 * @create: 2020-04-20 14:21
 **/
@Service
public class ProductInfoServiceImpl implements ProductInfoService {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    @Autowired
    private ProductInfoRepository productInfoRepository;
    @Override
    public Page<ProductInfo> listPage(ProductInfo vo, PageRequest pageable) {
        QProductInfo qp = QProductInfo.productInfo;

        QueryResults<ProductInfo> result = jpaQueryFactory.select(Projections.bean(ProductInfo.class, qp.id, qp.name, qp.count, qp.createDate, qp.updateDate))
                .from(qp)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize()).fetchResults();
        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }
    @Transactional
    @Override
    public Integer addProductInfo(ProductInfo vo) {
        productInfoRepository.save(vo);
        return 1;
    }
    @Transactional
    @Override
    public Integer updateProductInfo(ProductInfo vo) {
        productInfoRepository.save(vo);
        return 1;
    }
    @Transactional
    @Override
    public Integer deleteProductInfoById(Long id) {
        productInfoRepository.deleteById(id);
        return 1;
    }


}

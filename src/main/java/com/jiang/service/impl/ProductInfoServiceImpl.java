package com.jiang.service.impl;

import com.jiang.entity.ProductInfo;
import com.jiang.entity.QProductInfo;
import com.jiang.repository.ProductInfoRepository;
import com.jiang.service.ProductInfoService;
import com.jiang.vo.QueryVO;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.commons.lang3.StringUtils;
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
    public Page<ProductInfo> listPage(QueryVO vo, PageRequest pageable) {
        QProductInfo qp = QProductInfo.productInfo;

        JPAQuery<ProductInfo> query = jpaQueryFactory.selectFrom(qp).from(qp);
        
        if (StringUtils.isNotEmpty(vo.getProductName())){
            query.where(qp.name.stringValue().eq(vo.getProductName()));
        }
        QueryResults<ProductInfo> result = query
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(qp.createDate.desc())
                .fetchResults();
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
        QProductInfo qp = QProductInfo.productInfo;
        jpaQueryFactory.update(qp)
                .set(qp.name, vo.getName())
                .set(qp.updateDate, vo.getUpdateDate())
                .where(qp.id.longValue().eq(vo.getId()))
                .execute();

        return 1;
    }
    @Transactional
    @Override
    public Integer deleteProductInfoById(Long id) {
        productInfoRepository.deleteById(id);
        return 1;
    }


}

package com.jiang.service.impl;

import com.jiang.entity.FormBody;
import com.jiang.entity.QFormBody;
import com.jiang.repository.FormBodyRepository;
import com.jiang.repository.FormHeadRepository;
import com.jiang.repository.ProductInfoRepository;
import com.jiang.service.FormBodyService;
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
 * @create: 2020-04-20 15:43
 **/
@Service
public class FormBodyServiceImpl implements FormBodyService {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    @Autowired
    private FormBodyRepository formBodyRepository;
    @Autowired
    private FormHeadRepository formHeadRepository;
    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public Page<FormBody> queryPage(Long headId, PageRequest pageable) {
        QFormBody qfb = QFormBody.formBody;
        QueryResults<FormBody> result = jpaQueryFactory.select(
                Projections.bean(
                        FormBody.class,
                        qfb.id, qfb.headId, qfb.productId, qfb.count, qfb.createDate, qfb.updateDate))
                .where(qfb.headId.longValue().eq(headId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize()).fetchResults();
        return new PageImpl<>(result.getResults(),pageable, result.getTotal());
    }

    @Transactional
    @Override
    public Integer addFormBody(FormBody vo,Boolean flag) {
        formBodyRepository.save(vo);
        if(flag == false){
            vo.setCount(-vo.getCount());
        }
        productInfoRepository.updateCount(vo.getProductId(),vo.getCount());
        return 1;
    }

    @Transactional
    @Override
    public Integer updateFormBody(FormBody vo, Boolean flag) {
        FormBody bodyOld = formBodyRepository.findById(vo.getId()).get();
        formBodyRepository.save(vo);
        if(flag == true) {
            vo.setCount(vo.getCount()-bodyOld.getCount());
        }else vo.setCount(-vo.getCount()+bodyOld.getCount());
        productInfoRepository.updateCount(vo.getProductId(),vo.getCount());
        return 1;
    }

    @Transactional
    @Override
    public Integer deleteFormBodyById(Long id, Boolean flag) {
        FormBody bodyOld = formBodyRepository.findById(id).get();
        formBodyRepository.deleteById(id);
        if(flag == true) {
            bodyOld.setCount(-bodyOld.getCount());
        }else bodyOld.setCount(bodyOld.getCount());
        productInfoRepository.updateCount(bodyOld.getProductId(),bodyOld.getCount());
        return 1;
    }

}

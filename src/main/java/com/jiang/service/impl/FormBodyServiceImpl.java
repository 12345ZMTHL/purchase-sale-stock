package com.jiang.service.impl;

import com.jiang.entity.FormBody;
import com.jiang.entity.FormHead;
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
    public Integer addFormBody(FormBody vo,Integer flag) {
        Long count = flag == 1?vo.getCount():(-vo.getCount());
        productInfoRepository.updateCount(vo.getProductId(),count);
        formBodyRepository.save(vo);
        return 1;
    }

    @Transactional
    @Override
    public Integer updateFormBody(FormBody vo) {
        QFormBody qfb = QFormBody.formBody;
        //获取之前的单身
        FormBody bodyOld = formBodyRepository.findById(vo.getId()).get();
        //根据之前的单身获取单头
        FormHead formHead = formHeadRepository.findById(bodyOld.getHeadId()).get();
        Integer flag = formHead.getFlag();

        if (vo.getProductId() == bodyOld.getProductId()) {
            //只改变库存
            long count = flag == 1?(vo.getCount()-bodyOld.getCount()):(-vo.getCount()+bodyOld.getCount());
            productInfoRepository.updateCount(vo.getProductId(),count);
        }else {
            //库存和商品id都改变
            Long oldCount = flag == 1?-bodyOld.getCount():bodyOld.getCount();
            productInfoRepository.updateCount(bodyOld.getProductId(),oldCount);

            Long newCount = flag == 1?(vo.getCount()):(-vo.getCount());
            productInfoRepository.updateCount(vo.getProductId(),newCount);
        }
        jpaQueryFactory.update(qfb).set(qfb.count,vo.getCount()).set(qfb.productId,vo.getProductId()).where(qfb.id.longValue().eq(vo.getId())).execute();
        return 1;
    }

    @Transactional
    @Override
    public Integer deleteFormBodyById(Long id) {
        //获取之前的单身
        FormBody bodyOld = formBodyRepository.findById(id).get();
        //根据之前的单身获取单头
        FormHead formHead = formHeadRepository.findById(bodyOld.getHeadId()).get();
        Integer flag = formHead.getFlag();

        if(flag == 1) {
            bodyOld.setCount(-bodyOld.getCount());
        }else bodyOld.setCount(bodyOld.getCount());
        productInfoRepository.updateCount(bodyOld.getProductId(),bodyOld.getCount());
        formBodyRepository.deleteById(id);
        return 1;
    }

}

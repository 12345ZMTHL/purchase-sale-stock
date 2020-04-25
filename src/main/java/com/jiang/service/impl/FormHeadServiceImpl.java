package com.jiang.service.impl;

import com.jiang.dto.ProductInfoCountDTO;
import com.jiang.entity.FormBody;
import com.jiang.entity.FormHead;
import com.jiang.entity.QFormBody;
import com.jiang.entity.QFormHead;
import com.jiang.repository.FormBodyRepository;
import com.jiang.repository.FormHeadRepository;
import com.jiang.repository.ProductInfoRepository;
import com.jiang.service.FormHeadService;
import com.jiang.vo.QueryVO;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: purchase-sale-stock
 * @description:
 * @author: lvjx
 * @create: 2020-04-20 15:04
 **/
@Service
public class FormHeadServiceImpl implements FormHeadService {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Autowired
    private FormHeadRepository formHeadRepository;
    @Autowired
    private FormBodyRepository formBodyRepository;
    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public Page<FormHead> queryPage(QueryVO vo, PageRequest pageable) {
        QFormHead qfh = QFormHead.formHead;

        JPAQuery<FormHead> query = jpaQueryFactory.select(
                Projections.bean(FormHead.class, qfh.id, qfh.flag, qfh.createDate, qfh.updateDate))
                .from(qfh);
        if (null != vo.getFlag()){
            query.where(qfh.flag.eq(vo.getFlag()));
        }
        QueryResults<FormHead> result = query.offset(pageable.getOffset())
                .limit(pageable.getPageSize()).fetchResults();
        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }

    @Transactional
    @Override
    public Integer addFormHead(FormHead vo) {
        formHeadRepository.save(vo);
        return 1;
    }
    @Transactional
    @Override
    public Integer updateFormHead(FormHead vo) {
        QFormBody qfb = QFormBody.formBody;
        QFormHead qfh = QFormHead.formHead;

        FormHead old = formHeadRepository.findById(vo.getId()).get();
        if(vo.getFlag() != old.getFlag()){
            QueryResults<ProductInfoCountDTO> results = jpaQueryFactory.select(
                    Projections.bean(
                            ProductInfoCountDTO.class, qfb.productId, qfb.count))
                    .from(qfb)
                    .where(qfb.headId.longValue().eq(vo.getId()))
                    .fetchResults();
            for (ProductInfoCountDTO dto:results.getResults()){
                Long count = vo.getFlag() == 1?2*dto.getCount():(-dto.getCount())*2;
                productInfoRepository.updateCount(dto.getProductId(),count);
            }
        }
        jpaQueryFactory.update(qfh)
                .set(qfh.flag,vo.getFlag())
                .set(qfh.updateDate,vo.getUpdateDate())
                .where(qfh.id.longValue().eq(vo.getId()))
                .execute();
        return 1;
    }

    @Transactional
    @Override
    public Integer deleteFormHeadById(Long id) {
        FormBody body = new FormBody();
        body.setHeadId(id);
        Example<FormBody> example =Example.of(body);
        List<FormBody> list = formBodyRepository.findAll(example);
        if(list.size() > 0 && list != null){
            return 0;
        }
        formHeadRepository.deleteById(id);
        return 1;
    }
}

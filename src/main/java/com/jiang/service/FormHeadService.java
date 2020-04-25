package com.jiang.service;

import com.jiang.entity.FormHead;
import com.jiang.vo.QueryVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * @program: purchase-sale-stock
 * @description:
 * @author: lvjx
 * @create: 2020-04-20 15:04
 **/
public interface FormHeadService {
    Page<FormHead> queryPage(QueryVO vo, PageRequest pageable);

    Integer addFormHead(FormHead vo);
    Integer updateFormHead(FormHead vo);
    Integer deleteFormHeadById(Long id);
}

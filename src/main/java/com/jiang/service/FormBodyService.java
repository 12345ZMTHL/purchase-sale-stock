package com.jiang.service;

import com.jiang.entity.FormBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * @program: purchase-sale-stock
 * @description:
 * @author: lvjx
 * @create: 2020-04-20 15:43
 **/
public interface FormBodyService {
    Page<FormBody> queryPage(Long headId, PageRequest pageable);

    Integer addFormBody(FormBody vo,Integer flag);

    Integer updateFormBody(FormBody vo);

    Integer deleteFormBodyById(Long id);
}

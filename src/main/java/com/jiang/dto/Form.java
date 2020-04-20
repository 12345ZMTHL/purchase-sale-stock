package com.jiang.dto;

import com.jiang.entity.FormBody;
import com.jiang.entity.FormHead;
import lombok.Data;

import java.util.List;

/**
 * @program: purchase-sale-stock
 * @description: 单据信息
 * @author: lvjx
 * @create: 2020-04-20 15:20
 **/
@Data
public class Form {
    private FormHead head;
    private List<FormBody> bodys;
}

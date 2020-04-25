package com.jiang.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author 12345芝麻糖葫芦
 * @ClassName: QueryVO
 * @Description: 查询条件
 * @date 2020/4/25 15:23
 */
@Data
public class QueryVO {
    private String productName;
    private Integer flag;
    private Date startDate;
    private Date endDate;
}

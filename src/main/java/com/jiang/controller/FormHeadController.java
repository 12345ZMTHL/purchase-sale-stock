package com.jiang.controller;

import com.jiang.config.BaseResult;
import com.jiang.entity.FormHead;
import com.jiang.service.FormHeadService;
import com.jiang.vo.QueryVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @program: purchase-sale-stock
 * @description:
 * @author: lvjx
 * @create: 2020-04-20 15:02
 **/
@Api(value = "表单头操作")
@RestController
@RequestMapping("/formHead")
public class FormHeadController {
    @Autowired
    private FormHeadService formHeadService;

    @ApiOperation(value = "查询表单头信息")
    @GetMapping("/pageQuery")
    public Page<FormHead> queryPage(/**@RequestBody QueryVO vo,*/
                                       @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                                       @RequestParam(value = "pageSize", defaultValue = "30") Integer pageSize){
        PageRequest pageable = PageRequest.of(pageNo, pageSize);
        Page<FormHead> result = formHeadService.queryPage(new QueryVO(), pageable);
        return result;
    }

    @ApiOperation(value = "添加表单头")
    @PostMapping("/add")
    public Object addFormHead(@RequestBody FormHead vo){
        vo.setCreateDate(new Date());
        vo.setUpdateDate(vo.getCreateDate());
        formHeadService.addFormHead(vo);
        return null;
    }
    @ApiOperation("更新")
    @PostMapping("/update")
    public Object updateFormHead(@RequestBody FormHead vo){
        vo.setUpdateDate(new Date());
        formHeadService.updateFormHead(vo);
        return null;
    }
    @ApiOperation("删除")
    @PostMapping("delete")
    public Object deleteFormHeadById(@RequestParam("id") Long id){
        Integer row = formHeadService.deleteFormHeadById(id);
        if(row == 0){
            return  BaseResult.fail("该表单下存在单身信息，不允许删除");
        }
        return null;
    }
}

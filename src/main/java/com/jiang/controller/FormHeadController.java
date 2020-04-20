package com.jiang.controller;

import com.jiang.config.BaseResult;
import com.jiang.entity.FormHead;
import com.jiang.service.FormHeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

/**
 * @program: purchase-sale-stock
 * @description:
 * @author: lvjx
 * @create: 2020-04-20 15:02
 **/
@RestController
@RequestMapping("/formHead")
public class FormHeadController {
    @Autowired
    private FormHeadService formHeadService;

    @GetMapping("/queryPage")
    public Page<FormHead> queryPage(@RequestBody FormHead vo,
                                       @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                                       @RequestParam(value = "pageSize", defaultValue = "30") Integer pageSize){
        PageRequest pageable = PageRequest.of(pageNo, pageSize);
        Page<FormHead> result = formHeadService.queryPage(vo, pageable);
        return result;
    }

    @PostMapping("/add")
    public Object addFormHead(@RequestBody FormHead vo){
        formHeadService.addFormHead(vo);
        return null;
    }
    @PostMapping("/update")
    public Object updateFormHead(@RequestBody FormHead vo){
        formHeadService.addFormHead(vo);
        return null;
    }
    @PostMapping("delete")
    public Object deleteFormHeadById(@RequestParam("id") Long id){
        Integer row = formHeadService.deleteFormHeadById(id);
        if(row == 0){
            return  BaseResult.fail("该表单下存在单身信息，不允许删除");
        }
        return null;
    }
}

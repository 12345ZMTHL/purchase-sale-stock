package com.jiang.controller;

import com.jiang.config.interceptor.AnalysisaAnotation;
import com.jiang.entity.FormBody;
import com.jiang.service.FormBodyService;
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
 * @create: 2020-04-20 15:42
 **/
@Api(value = "单身")
@RestController
@RequestMapping("formBody")
public class FormBodyController {
    @Autowired
    private FormBodyService formBodyService;

    @ApiOperation(value = "查询")
    @GetMapping("pageQuery")
    @AnalysisaAnotation(flagAnalysis = true,eventId = 12)
    public Page<FormBody> queryPage(@RequestParam(value = "headId") Long headId,
                                    @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                                    @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){
        PageRequest pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<FormBody> result = formBodyService.queryPage(headId,pageable);
        return result;
    }
    @ApiOperation(value = "添加")
    @PostMapping("/add")
    public Object addFormBody(@RequestBody FormBody vo,@RequestParam("flag") Integer flag){
        vo.setCreateDate(new Date());
        vo.setUpdateDate(vo.getCreateDate());
        formBodyService.addFormBody(vo,flag);
        return null;
    }

    @ApiOperation(value = "更新")
    @PostMapping("/update")
    public Object updateFormBody(@RequestBody FormBody vo){
        vo.setUpdateDate(new Date());
        formBodyService.updateFormBody(vo);
        return null;
    }
    @ApiOperation(value = "删除")
    @PostMapping("/delete")
    public Object deleteFormBodyById(@RequestParam("id") Long id){
        formBodyService.deleteFormBodyById(id);
        return null;
    }
}

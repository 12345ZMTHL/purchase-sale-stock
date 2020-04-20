package com.jiang.controller;

import com.jiang.entity.FormBody;
import com.jiang.entity.FormHead;
import com.jiang.entity.ProductInfo;
import com.jiang.service.FormBodyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

/**
 * @program: purchase-sale-stock
 * @description:
 * @author: lvjx
 * @create: 2020-04-20 15:42
 **/
@RestController
@RequestMapping("formBody")
public class FormBodyController {
    @Autowired
    private FormBodyService formBodyService;

    @GetMapping("queryPage")
    public Page<FormBody> queryPage(@RequestParam(value = "id") Long id,
                                    @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                                    @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){
        PageRequest pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<FormBody> result = formBodyService.queryPage(id,pageable);
        return result;
    }

    @PostMapping("/add")
    public Object addFormBody(@RequestBody FormBody vo,@RequestParam("flag") Boolean flag){
        formBodyService.addFormBody(vo,flag);
        return null;
    }

    @PostMapping("/update")
    public Object updateFormBody(@RequestBody FormBody vo,@RequestParam("flag") Boolean flag){
        formBodyService.updateFormBody(vo,flag);
        return null;
    }

    @PostMapping("/delete")
    public Object deleteFormBodyById(@RequestParam("id") Long id,@RequestParam("flag") Boolean flag){
        formBodyService.deleteFormBodyById(id,flag);
        return null;
    }
}

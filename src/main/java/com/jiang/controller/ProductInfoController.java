package com.jiang.controller;

import com.jiang.entity.ProductInfo;
import com.jiang.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: purchase-sale-stock
 * @description: 商品详细查询
 * @author: lvjx
 * @create: 2020-04-20 14:16
 **/
@RestController
@RequestMapping("productInfo")
public class ProductInfoController {
    @Autowired
    private ProductInfoService productInfoService;

    @GetMapping("/pageQuery")
    public Page<ProductInfo> pageQuery(@RequestBody ProductInfo vo,
                                       @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                                       @RequestParam(value = "pageSize", defaultValue = "30") Integer pageSize){
        PageRequest pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<ProductInfo> orderInfoDTOPage = productInfoService.listPage(vo, pageable);
        return orderInfoDTOPage;
    }

    @PostMapping("/add")
    public Object addProductInfo(@RequestBody ProductInfo vo){
        vo.setCount(null);
        productInfoService.addProductInfo(vo);
        return null;
    }

    @PostMapping("/update")
    public Object updateProductInfo(@RequestBody ProductInfo vo){
        vo.setCount(null);//库存不允许在商品这里修改
        productInfoService.updateProductInfo(vo);
        return null;
    }
    @PostMapping("/delete")
    public Object deleteProductInfoById(@RequestParam(name = "id") Long id){
        productInfoService.deleteProductInfoById(id);
        return null;
    }
}

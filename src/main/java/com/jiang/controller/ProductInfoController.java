package com.jiang.controller;

import com.jiang.entity.ProductInfo;
import com.jiang.service.ProductInfoService;
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
 * @description: 商品详细查询
 * @author: lvjx
 * @create: 2020-04-20 14:16
 **/
@Api(value = "商品")
@RestController
@RequestMapping("productInfo")
public class ProductInfoController {
    @Autowired
    private ProductInfoService productInfoService;

    @ApiOperation(value = "查询")
    @GetMapping("/pageQuery")
    public Page<ProductInfo> pageQuery(@RequestBody QueryVO vo,
                                       @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                                       @RequestParam(value = "pageSize", defaultValue = "30") Integer pageSize){
        PageRequest pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<ProductInfo> orderInfoDTOPage = productInfoService.listPage(vo, pageable);
        return orderInfoDTOPage;
    }

    @ApiOperation(value = "添加")
    @PostMapping("/add")
    public Object addProductInfo(@RequestBody ProductInfo vo){
        vo.setCreateDate(new Date());
        vo.setUpdateDate(vo.getCreateDate());
        vo.setCount(0L);//添加商品默认数量为0
        productInfoService.addProductInfo(vo);
        return null;
    }

    @ApiOperation(value = "更新")
    @PostMapping("/update")
    public Object updateProductInfo(@RequestBody ProductInfo vo){
        vo.setCount(null);//库存不允许在商品这里修改
        vo.setUpdateDate(new Date());
        productInfoService.updateProductInfo(vo);
        return null;
    }

    @ApiOperation(value = "删除")
    @PostMapping("/delete")
    public Object deleteProductInfoById(@RequestParam(name = "id") Long id){
        productInfoService.deleteProductInfoById(id);
        return null;
    }
}

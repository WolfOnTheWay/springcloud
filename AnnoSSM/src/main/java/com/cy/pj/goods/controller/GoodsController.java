package com.cy.pj.goods.controller;

import com.cy.pj.goods.pojo.Goods;
import com.cy.pj.goods.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/goods/")
public class GoodsController {
    @Autowired
    @Qualifier("goodsServiceImpl")
    private GoodsService goodsService;


    @GetMapping("doFind")
    @ResponseBody
    public List<Goods> doFindObjects(){
        System.out.println("rotuine");
        return goodsService.findGoods();
    }
}

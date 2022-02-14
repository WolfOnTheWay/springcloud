package com.cy.pj.controller;

import com.cy.pj.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;
    @RequestMapping("/deleteGoods")
    @ResponseBody
    public String doDeleteObjects()
    {
        goodsService.deleteObjects(1);
        return "delete ok";
    }

    @RequestMapping("/goodsUI")
    public String goodsUI()
    {
        System.out.println("goodsUI");
        return "goods";
    }
}

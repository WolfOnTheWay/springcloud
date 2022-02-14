package com.jt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 李志杰
 * @version 1.0
 * @description:TODO
 * @date 2022/1/6 17:02
 */
@Controller
public class IndexController {
    /*  /page/item-add'
        /page/item-list'
        /page/item-param-list'}">
    * */
    @RequestMapping("/page/{moduleName}")
    public String item_add(@PathVariable String moduleName){
        return moduleName;
    }

    @RequestMapping("/getMsg")
    @ResponseBody
    public String getMsg(){
        return "我是8083";
    }

}

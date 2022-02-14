package com.cy.pi.sys.controller;
/*
* 负责呈现所有的页面
* */
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PageController {
    //首页
    @RequestMapping("doIndexUI")
    public String doIndexUI(){
        return "starter";
    }
    //分页页面
    @RequestMapping("doPageUI")
    public String doPageUI(){
        return "common/page";
    }

    @RequestMapping("doLoginUI")
    public String doLoginUI(){
        return "login";
    }

//    //日志列表页面
//    @RequestMapping("/log/log_list")
//    public String doLogUI(){
//        return "sys/log_list";
//    }
//
//
//    @RequestMapping("sys/menu_list")
//    public String doMenuListUI(){
//        return "sys/menu_list";
//    }
    /*返回某个模块的UI页面*/
    //pathVariable表示参数的值来源于请求路径
    @RequestMapping("{module}/{ui}")
    public String doMoudleUI(@PathVariable String ui){
        return "sys/"+ ui;
    }
}

package com.cy.pj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @RequestMapping("d0SayHello")
    @ResponseBody
    public String doSayHello(){
        return "hello spring boot";
    }
}

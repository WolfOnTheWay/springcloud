package com.tedu.controller;

import com.tedu.dao.DoorMapper;
import com.tedu.pojo.Door;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class TestSSM {
    /** 自动装配：由spring自动为属性赋值(对象)  */
    @Autowired
    DoorMapper mapper;

    @RequestMapping("/testssm")
    public String testSSM(){
        //1.调用findAll方法查询所有门店信息
        List<Door> list = mapper.findAll();
        //2.遍历所有门店信息
        for(Door door : list){
            System.out.println(door);
        }
        return "test";
    }
}

package com.tedu.controller;


import com.tedu.dao.DoorMapper;
import com.tedu.pojo.Door;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class DoorController {
    @Autowired
    DoorMapper dao;

    @RequestMapping("/doorList")
    public String doorList(Model model){
        List<Door> list = dao.findAll();
        for (Door d:
             list) {
            System.out.println(d);
        }
            model.addAttribute("list",list);
        return "door_list";
    }

    @RequestMapping("doorDelete")
    public String doorDelete(Integer id){
        dao.deleteById(id);
        return "forward:doorList";
    }

    @RequestMapping("doorAdd")
    public String doorAdd(Door door){
        dao.add(door);
        return "forward:doorList";
    }

    @RequestMapping("doorInfo")
    public String doorInfo(Integer id,Model model){
        Door door= dao.selectById(id);
        model.addAttribute("door",door);
        return "door_info";
    }
    @RequestMapping("doorUpdate")
    public String doorUpdate(Door door){
        dao.updateById(door);
        return "forward:doorList";
    }
    @RequestMapping("/{page}")
    public String toPage(@PathVariable String page){//注解接收变量的page值
        return page;
    }
}

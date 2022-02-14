package com.jt.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jt.service.UserService;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisCluster;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 李志杰
 * @version 1.0
 * @description:TODO
 * @date 2022/1/21 8:55
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private JedisCluster jedisCluster;
    @Autowired
    private UserService userService;

    /**
     *@Description:[jsonp跨域]
     *@Author:李志杰
     *@Date:2022/1/21
     *@Param: callback：跨域中的回调函数
     */
    @RequestMapping("/check/{param}/{type}")
    public JSONPObject checkUser(@PathVariable String param,@PathVariable Integer type,String callback){
        boolean flag = userService.checkUser(param,type);
        return new JSONPObject(callback, SysResult.success(flag));
    }
    @RequestMapping("/query/{ticket}")
    public JSONPObject findUserByTicket(@PathVariable String ticket, String callback){
        String userJson = jedisCluster.get(ticket);
        return new JSONPObject(callback,SysResult.success(userJson));
    }


}

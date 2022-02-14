package com.jt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jt.pojo.User;
import com.jt.service.DubboUserService;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.JedisCluster;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 李志杰
 * @version 1.0
 * @description:TODO
 * @date 2022/1/20 15:34
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private JedisCluster jedisCluster;
    @Reference(check = false)
    private DubboUserService dubboUserService;
    @RequestMapping("/{module}")
    public String module(@PathVariable String module){
        return module;
    }
    //rpc

    @RequestMapping("/doRegister")
    @ResponseBody
    public SysResult saveUser(User user){
        dubboUserService.saveUser(user);
        return SysResult.success();
    }

    @RequestMapping("/doLogin")
    @ResponseBody
    public SysResult login(User user, HttpServletResponse response){
        //校验数据是否正确，获取密钥
        String ticket =dubboUserService.findUserByUp(user);
        if(StringUtils.isEmpty(ticket))
        {
            return  SysResult.fail("用户名或者密码错误");
        }
        //密钥有值写入cookie
        Cookie cookie = new Cookie("JT_TICKET",ticket);
        cookie.setMaxAge(7*24*3600);
        //全部的请求都可见该cookie信息
        cookie.setPath("/");
        //设定cookie共享 //sso
        cookie.setDomain("jt.com");
        //将cookie写入客户端
        response.addCookie(cookie);
        return SysResult.success();
    }

    /**
     * 实现用户登出操作.
     * 	0.获取cookie数据
     * 	1.删除redis  key~~~ticket
     * 	2.删除cookie
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        String ticket = null;
        //判断cookie是有效的.
        if (cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if ("JT_TICKET".equals(cookie.getName())) {
                    ticket = cookie.getValue();
                    break;
                }
            }
        }
        if (!StringUtils.isEmpty(ticket)) {
            //如果数据不为null,则删除数据
            jedisCluster.del(ticket);
            Cookie cookie = new Cookie("JT_TICKET", "");
            cookie.setMaxAge(0); //立即删除
            cookie.setPath("/");
            cookie.setDomain("jt.com");
            response.addCookie(cookie);
        }
        //重定向到系统首页
        System.out.println("退出成功");
        return "redirect:/";
    }

}

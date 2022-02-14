package com.jt.interceptor;

import com.jt.pojo.User;
import com.jt.util.ObjectMapperUtil;
import com.jt.util.UserThreadLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import redis.clients.jedis.JedisCluster;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 李志杰
 * @version 1.0
 * @description:TODO
 * @date 2022/2/3 15:31
 */
@Component
public class UserInterceptor implements HandlerInterceptor {

    private static final String ticket="JT_TICKET";
    @Autowired
    private JedisCluster jedisCluster;
    /*
    * 实现权限认证，用户不登陆，不允许访问涉密操作，且跳转到登录界面
    *
    * 返回true代表放行，false代表拦截
    * */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断cookie是有效的.
        Cookie[] cookies = request.getCookies();
        String ticket = null;
        if (cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if ("JT_TICKET".equals(cookie.getName())) {
                    ticket = cookie.getValue();
                    break;
                }
            }
        }
        if(!StringUtils.isEmpty(ticket)){
            //校验redis是否有数据
            String userJson = jedisCluster.get(ticket);
            if(!StringUtils.isEmpty(userJson)){
                //实现用户信息的动态获取
                User user = ObjectMapperUtil.toObject(userJson, User.class);
//                //利用request域将用户信息带到controller
//                request.setAttribute("JT_USER",user);
                UserThreadLocal.set(user);
                return true;
            }
        }

        //如果用户没有登录，则重定向登录页面
        response.sendRedirect("/user/login.html");
        return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //移除线程变量中的内容
        UserThreadLocal.remove();
    }
}

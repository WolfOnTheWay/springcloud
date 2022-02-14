package com.cy.pi.common.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;

/**
 * @author 李志杰
 * @version 1.0
 * @description:TODO
 * @date 2021/12/20 20:47
 */
@Configuration
public class WebFilterConfig {
    @Bean("filterRegistrationBean")
    public FilterRegistrationBean<Filter> newFilterRegistrationBean(){
        //过滤器注册器
        FilterRegistrationBean<Filter> fBean = new FilterRegistrationBean<>();
        //srping中的初始化filter的对象，传入bean的key值
        DelegatingFilterProxy filter = new DelegatingFilterProxy("shiroFilterFactoryBean");
        fBean.setFilter(filter);
        //配置映射路径
        fBean.addUrlPatterns("/*");
        return fBean;
    }
}

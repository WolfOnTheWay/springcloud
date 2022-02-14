package com.cy.pi.common.config;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.LinkedHashMap;

/**
 * @author 李志杰
 * @version 1.0
 * @description: TODO
 * @date 2021/12/20 20:17
 */
@Configuration
public class SpringShiroConfig {
    /*shiro的核心管理器对象，负责认证和授权操作*/
    @Bean
    public SecurityManager securityManager(@Autowired Realm rm,@Autowired CacheManager cacheManager){
        DefaultWebSecurityManager sm = new DefaultWebSecurityManager();
        sm.setRealm(rm);
        sm.setCacheManager(cacheManager);
        return sm;
    }
    /*负责创建过滤器工厂，通过工厂来创建过滤器*/
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Autowired SecurityManager sm){
        ShiroFilterFactoryBean fBean = new ShiroFilterFactoryBean();
        fBean.setSecurityManager(sm);
        //设置登录界面，当访问需要认证的页面时会跳转到此页面
        fBean.setLoginUrl("/doLoginUI");
        //配置过滤规则
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        //anon代表允许匿名访问，放行的要写在上面
        map.put("/bower_components/**","anon");
        map.put("/build/**","anon");
        map.put("/dist/**","anon");
        map.put("/plugins/**","anon");
        map.put("/user/doLogin","anon");
        map.put("/static/**","anon");

        //除了上面的那些剩下的都有认证
        map.put("/**","authc");//authc表示要认证
        fBean.setFilterChainDefinitionMap(map);

        return fBean;
    }



    //生命周期管理器
    @Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor newLifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }
    //创建负责创建代理对象的对象,扫面项目中所有的Advisor对象
    //并未这些对象创建代理对象
    @DependsOn("lifecycleBeanPostProcessor")
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        return new DefaultAdvisorAutoProxyCreator();
    }

    /**
     * @Description: 此对象实现了spring中的Advisor接口，此对象中要提供切入点，还要关联通知对象进行
     * 功能扩展。
     * @Author: 李志杰
     * @Date: 2022/1/3
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Autowired SecurityManager sm){
        AuthorizationAttributeSourceAdvisor advisor =
                new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(sm);
        return advisor;
    }

    //缓存,防止每次都进行权限查询
    @Bean
    public CacheManager cacheManager(){
        return new MemoryConstrainedCacheManager();
    }

}

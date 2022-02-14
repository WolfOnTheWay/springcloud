package com.sdt.fsc.config;


import com.sdt.fsc.service.impl.system.ShiroRealmImpl;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;


@Configuration
@CrossOrigin
public class ShiroConfig {

    // 将自己的验证方式加入容器
    @Bean
    public ShiroRealmImpl shiroRealm() {
        ShiroRealmImpl shiroRealm = new ShiroRealmImpl();
        return shiroRealm;
    }

    @Bean
    public SessionManager sessionManager(){
        SessionManager sessionManager = new SessionManager();
        return sessionManager;
    }

    // 权限管理，配置主要是Realm的管理认证
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm());
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    // Filter工厂，设置对应的过滤条件和跳转条件
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> filterMap = new HashMap<>();
        //登出
        filterMap.put("/api/v1/system/logout","anon");
        //登录
        filterMap.put("/api/v1/system/login","anon");
       // filterMap.put("/api/v1/system/addUser","anon");

        //swagger
        filterMap.put("/swagger**/**", "anon");
        filterMap.put("/webjars/**","anon");
        filterMap.put("/v2/**","anon");
        filterMap.put("/images/**","anon");
        filterMap.put("/configuration/**","anon");
        // 对所有用户认证
       /* filterMap.put("/**", "authc");*/
        // 未登录跳转
        shiroFilterFactoryBean.setLoginUrl("/api/v1/system/unauth");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
        filters.put("authc",new ShiroPassOptionsFilter());
        shiroFilterFactoryBean.setFilters(filters);
        return shiroFilterFactoryBean;
    }

    // 加入注解的使用，不加入这个注解不生效
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}

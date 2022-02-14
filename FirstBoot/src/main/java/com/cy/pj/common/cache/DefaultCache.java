package com.cy.pj.common.cache;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class DefaultCache {
    public DefaultCache(){
        System.out.println("public DefaultCache()");
    }
    @PostConstruct
    public void init(){}
    @PreDestroy
    public void destroy(){}
}

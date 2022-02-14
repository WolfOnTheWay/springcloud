package org.beans;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//表示getBean时都创建出一个新的实例
@Scope("prototype")
@Component
public class DefaultCache {
    public DefaultCache(){
        System.out.println("DefaultCache");
    }
    //表示在对象初始化时会执行此方法（此方法在构造方法之后执行）
    @PostConstruct
    public void init(){
        System.out.println("DefaultCache init()");
    }
    //在Spring容器关闭时执行此方法Scope为Singletor
    @PreDestroy
    public void destory(){
        System.out.println("DefaultCache destory()");
    }
}

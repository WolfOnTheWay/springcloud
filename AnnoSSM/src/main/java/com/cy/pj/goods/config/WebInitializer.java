package com.cy.pj.goods.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
/*初始化的类,取代web.xml*/
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    /*service和dao*/
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SpringRepositoryConfig.class,SpringServiceConfig.class};
    }
    //controller
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringWebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}

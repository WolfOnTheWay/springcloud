package org.example;

import com.alibaba.druid.pool.DruidDataSource;
import org.beans.DefaultCache;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

public class TestBase {
    protected AnnotationConfigApplicationContext ctx;
    @Before
    public void init(){
       /* ctx = new ClassPathXmlApplicationContext("spring-context.xml");*/
         ctx = new AnnotationConfigApplicationContext(SpringConfig.class);

    }
    @Test
    public void testCtx(){
        /*System.out.println(ctx);*/
        DefaultCache cache = ctx.getBean("defaultCache", DefaultCache.class);
        System.out.println(cache);
    }
    @Test
    public void testBeans01() throws SQLException {
        DruidDataSource dataSource = ctx.getBean("dataSource", DruidDataSource.class);
        System.out.println(dataSource.getConnection());
    }
    
    @After //在Test运行完之后会执行此方法
    public void close(){
        ctx.close();
    }
}

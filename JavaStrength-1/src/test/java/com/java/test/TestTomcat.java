package com.java.test;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.junit.Test;

public class TestTomcat {
    @Test
    public void testTomcat() throws LifecycleException, InterruptedException {
//        /*构建tomcat对象*/
//        Tomcat t = new Tomcat();
//        /*构建Connector对象*/
//        Connector c= new Connector("HTTP/1.1");
//        //设置超时时间
//        c.setAsyncTimeout(20000);
//        c.setPort(8080);
//        t.getService().addConnector(c);
//        t.start();
//        t.getServer().await();
        Integer a1 =100;
        Integer a2 =100;
        Integer a3 =200;
        Integer a4 =200;
        System.out.println(a1==a2);
        System.out.println(a3==a4);

    }


}

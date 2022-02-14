package com.tedu.sp11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.EnableZuulServer;

/**
 * @author 李志杰
 * @version 1.0
 * @description:TODO
 * @date 2022/2/10 10:07
 */
@EnableDiscoveryClient
@EnableZuulProxy
@SpringBootApplication
public class Sp11ZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(Sp11ZuulApplication.class,args);
    }
}

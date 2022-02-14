package com.tedu.sp05;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author 李志杰
 * @version 1.0
 * @description:TODO
 * @date 2022/2/8 13:52
 */
@SpringBootApplication
@EnableEurekaServer
public class Sp05EurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(Sp05EurekaApplication.class,args);
    }
}

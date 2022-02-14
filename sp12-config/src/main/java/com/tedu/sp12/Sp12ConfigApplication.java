package com.tedu.sp12;

/**
 * @author 李志杰
 * @version 1.0
 * @description:TODO
 * @date 2022/2/14 16:50
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@EnableDiscoveryClient
@SpringBootApplication
public class Sp12ConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(Sp12ConfigApplication.class, args);
    }
}
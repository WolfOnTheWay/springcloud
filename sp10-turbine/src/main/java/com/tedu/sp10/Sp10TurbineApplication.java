package com.tedu.sp10;

/**
 * @author 李志杰
 * @version 1.0
 * @description:TODO
 * @date 2022/2/9 19:41
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@EnableTurbine
@EnableDiscoveryClient
@SpringBootApplication
public class Sp10TurbineApplication {
    public static void main(String[] args) {
        SpringApplication.run(Sp10TurbineApplication.class, args);
    }
}
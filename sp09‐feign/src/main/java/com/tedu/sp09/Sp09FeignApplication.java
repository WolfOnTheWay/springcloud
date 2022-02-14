package com.tedu.sp09;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 李志杰
 * @version 1.0
 * @description:TODO
 * @date 2022/2/9 15:21
 */
@EnableCircuitBreaker
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class Sp09FeignApplication {
    public static void main(String[] args) {
        SpringApplication.run(Sp09FeignApplication.class,args);
    }
}

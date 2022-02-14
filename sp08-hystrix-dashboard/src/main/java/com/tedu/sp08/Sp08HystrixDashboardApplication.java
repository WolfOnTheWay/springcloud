package com.tedu.sp08;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @author 李志杰
 * @version 1.0
 * @description:TODO
 * @date 2022/2/9 14:17
 */
@EnableDiscoveryClient
@EnableHystrixDashboard
@SpringBootApplication
public class Sp08HystrixDashboardApplication {
    public static void main(String[] args) {
        SpringApplication.run(Sp08HystrixDashboardApplication.class,args);
    }
}

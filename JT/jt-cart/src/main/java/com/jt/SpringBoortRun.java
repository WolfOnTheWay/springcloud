package com.jt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 李志杰
 * @version 1.0
 * @description:TODO
 * @date 2022/1/30 19:49
 */
@SpringBootApplication
@MapperScan("com.jt.mapper")
public class SpringBoortRun {
    public static void main(String[] args) {
        SpringApplication.run(SpringBoortRun.class,args);
    }
}

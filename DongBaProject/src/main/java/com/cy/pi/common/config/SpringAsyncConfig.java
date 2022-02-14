package com.cy.pi.common.config;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 李志杰
 * @version 1.0
 * @description:TODO
 * @date 2021/12/19 15:27
 */
@Configuration
public class SpringAsyncConfig {
    private int corPoolSize = 3;
    private int maxnumPoolSize = 5;
    private int keepAliveTIme = 30;
    /*构建线程工厂*/
    private ThreadFactory threadFactory= new ThreadFactory(){
        private AtomicInteger atomicInteger = new AtomicInteger(1000);
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "内部线程"+atomicInteger.getAndIncrement());
        }
    };
    //在@Async中使用bean名称
    @Bean("threadPoolExecutor")
    public ThreadPoolExecutor newPoolExecutor(){
        BlockingDeque<Runnable> workqueue = new LinkedBlockingDeque<>(10);
        //最大并发量为15，最大线程为5，等待任务为10
        ThreadPoolExecutor threadPoolExecutor= new ThreadPoolExecutor(corPoolSize,maxnumPoolSize,keepAliveTIme, TimeUnit.SECONDS,workqueue,threadFactory);
        System.out.println("内部线程池创建完毕");
        return threadPoolExecutor;

    }
}

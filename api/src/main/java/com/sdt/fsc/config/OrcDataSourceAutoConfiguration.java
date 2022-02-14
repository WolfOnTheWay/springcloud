package com.sdt.fsc.config;

import com.alibaba.druid.pool.DruidDataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties(OrcDriverProperties.class)
public class OrcDataSourceAutoConfiguration {

    @Resource
    private OrcDriverProperties orcDriverProperties;

    @Bean(name = "customDatasource")
    public DruidDataSource customDatasource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(orcDriverProperties.getJdbcUrl());
        druidDataSource.setUsername(orcDriverProperties.getUser());
        druidDataSource.setPassword(orcDriverProperties.getPassword());
        druidDataSource.setDriverClassName(orcDriverProperties.getDriverName());
        //初始化容量大小
        druidDataSource.setInitialSize(10);
        //最大连接数
        druidDataSource.setMaxActive(10);
        druidDataSource.setMaxWait(60000);
        //设置一次连接的最小时间为3分钟
        druidDataSource.setMinEvictableIdleTimeMillis(180000);
        druidDataSource.setKeepAlive(true);
        //自动超时连接回收开启
        druidDataSource.setRemoveAbandoned(true);
        //数据服务宕机自动重试机制
        druidDataSource.setBreakAfterAcquireFailure(true);
        //检测心跳
        druidDataSource.setTimeBetweenEvictionRunsMillis(60000);
        druidDataSource.setTestOnBorrow(true);
        druidDataSource.setTestOnReturn(true);
        //连接出錯后，尝试连接3次
        druidDataSource.setConnectionErrorRetryAttempts(3);
        //设置连接心跳语句
        druidDataSource.setValidationQuery("select 1 from system.dual");
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(10);
        return druidDataSource;
    }

    @Bean(name = "hiveDruidTemplate")
    public JdbcTemplate hiveDruidTemplate(@Qualifier("customDatasource") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}

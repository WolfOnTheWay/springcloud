package com.cy.pj.goods.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/*
* 数据层配置对象
* */
@Configuration
@MapperScan("com.cy.pj.goods.dao")
public class SpringRepositoryConfig {
    @Bean(value = "druid")
    public DataSource newDruid(){
        DruidDataSource ds = new DruidDataSource();
        ds.setUrl("jdbc:mysql:///goods?serverTimezone=GMT%2B8");
        ds.setUsername("root");
        ds.setPassword("duang521");
        ds.setMaxWait(5000);
        return ds;
    }

    @Autowired
    @Bean("sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(
           @Qualifier("druid") DataSource dataSource
    ) throws Exception{
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        return factoryBean.getObject();
    }
}

package org.beans;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import javax.sql.DataSource;
//@Configuration一般用于描述配置类对象
//一般会在这样的对象内部整合第三发Bean对象

@Configuration //类似于Component
public class DataSourceConfig {
    //@Bean描述的方法返回值会交给spring容器来管理
    @Lazy(value = false)
    @Bean(value = "dataSource")//默认key为方法名
    public DataSource newDruidDataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql:///tables?serverTimezone=GMT");
        dataSource.setUsername("root");
        dataSource.setPassword("duang521");
        return dataSource;
    }
}

package com.sdt.fsc.config;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.sdt.fsc.mapper.mysql2", sqlSessionFactoryRef = "mysql2SqlSessionFactory")
public class Mysql2DataSourceConfig {
    @Bean(name = "mysql2DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mysql2") //读取配置文件中的数据源，和你自己配置的数据源前缀保持一致
    @Qualifier("mysql2DataSource")
    public DataSource mysql2DataSource() {
        return DataSourceBuilder.create().build();
    }

    //配置第二个数据源的事务
    @Bean(name = "mysql2TransactionManager")
    public DataSourceTransactionManager mysql2TransactionManager(@Qualifier("mysql2DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
    @Bean(name = "mysql2SqlSessionFactory")
    public SqlSessionFactory mysql2SqlSessionFactory(@Qualifier("mysql2DataSource") DataSource dataSource)
            throws Exception {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //mybatis写配置文件（sql映射）需要加下面的代码
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath:mapper/xml/mysql2/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "mysql2SqlSessionTemplate")
    public SqlSessionTemplate mysql2SqlSessionTemplate(
            @Qualifier("mysql2SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}

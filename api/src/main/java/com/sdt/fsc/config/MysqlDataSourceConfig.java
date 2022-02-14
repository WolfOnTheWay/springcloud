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
@MapperScan(basePackages = "com.sdt.fsc.mapper.mysqlmapper", sqlSessionFactoryRef = "mysqlSqlSessionFactory")
public class MysqlDataSourceConfig {
    @Bean(name = "mysqlDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mysql") //读取配置文件中的数据源，和你自己配置的数据源前缀保持一致
    @Qualifier("mysqlDataSource") //指定数据源名称
    @Primary //多个数据源时，一定要有一个数据源加上@Primary注解，否则启动时可能会报类似这样的异常，expected single matching bean but found 2:
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    //配置主数据源的事务
    @Bean(name = "mysqlTransactionManager")
    @Primary
    public DataSourceTransactionManager primaryTransactionManager(@Qualifier("mysqlDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
    @Bean(name = "mysqlSqlSessionFactory")
    @Primary
    public SqlSessionFactory primarySqlSessionFactory(@Qualifier("mysqlDataSource") DataSource dataSource)
            throws Exception {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //mybatis写配置文件（sql映射）需要加下面的代码
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath:mapper/xml/mysql/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "mysqlSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate primarySqlSessionTemplate(
            @Qualifier("mysqlSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}

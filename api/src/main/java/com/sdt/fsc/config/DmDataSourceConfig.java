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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.sdt.fsc.mapper.dm", sqlSessionTemplateRef = "dmSqlSessionTemplate")
public class DmDataSourceConfig {

    @Bean(name = "dmDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.dm") //读取配置文件中的数据源，和你自己配置的数据源前缀保持一致
    @Qualifier("dmDataSource") //指定数据源名称
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "dmSqlSessionFactory")
    public SqlSessionFactory secondarySqlSessionFactory(@Qualifier("dmDataSource") DataSource dataSource)
            throws Exception {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //mybatis写配置文件（sql映射）需要加下面的代码
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath:mapper/xml/dm/*.xml"));
        return bean.getObject();
    }
    @Bean(name = "dmSqlSessionTemplate")
    public SqlSessionTemplate secondarySqlSessionTemplate(
            @Qualifier("dmSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}

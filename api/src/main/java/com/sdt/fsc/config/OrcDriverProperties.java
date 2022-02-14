package com.sdt.fsc.config;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "spring.datasource")
public class OrcDriverProperties {

    @Value("${spring.datasource.hive-weather.driver-class-name}")
    private String driverName;
    @Value("${spring.datasource.hive-weather.jdbcUrl}")
    private String jdbcUrl;
    @Value("${spring.datasource.hive-weather.username}")
    private String user ;
    @Value("${spring.datasource.hive-weather.password}")
    private String password;
}

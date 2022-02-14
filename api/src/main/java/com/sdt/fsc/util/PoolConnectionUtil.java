package com.sdt.fsc.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.LinkedList;


/**
 * @author 陈斌
 */
//@Component
@Slf4j
public class PoolConnectionUtil implements InitializingBean {

    @Value("${spring.datasource.hive-weather.driver-class-name}")
    public  String driverName;
    @Value("${spring.datasource.hive-weather.jdbcUrl}")
    public  String jdbcUrl;
    @Value("${spring.datasource.hive-weather.username}")
    public  String user ;
    @Value("${spring.datasource.hive-weather.password}")
    public  String password;
    private LinkedList<Connection> pools = new LinkedList<Connection>();
    @Override
    public void afterPropertiesSet() throws Exception {
        //一次性创建10个连接
        for (int i = 0; i < 10; i++) {
            Connection connection = getOneConnection();
            pools.add(connection);
        }
    }
    /**
     * 一次性创建10个连接
     */
 /*   public PoolConnectionUtil(){
        for (int i = 0; i < 10; i++) {
            Connection connection = getOneConnection();
            pools.add(connection);
        }
    }*/

    public Connection getOneConnection(){
        Connection connection = null;
        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(jdbcUrl,user,password);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public  Connection getConnection() throws SQLException {
        Connection connection = pools.removeFirst();
        log.info("当前连接：{} ,连接池的数量为：{}",connection,pools.size());
        return connection;
    }

    /**
     * 将连接放回池中
     * @param connection
     */
    public void releaseConnection(Connection connection){
        pools.add(connection);
        log.info("将连接放回到连接池中 {}, 当前池中数量为 {}",connection,pools.size());
    }

    public  void release(Connection connection, Statement statement , ResultSet resultSet){
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
}

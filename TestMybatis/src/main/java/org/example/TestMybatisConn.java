package org.example;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.beans.Encoder;
import java.io.InputStream;
import java.sql.Connection;

interface A{
   
}
public class TestMybatisConn {
    protected SqlSessionFactory factory;

    /*@Before在@Test之前执行*/
    @Before
    public void init() throws Exception {
        InputStream in = Resources.getResourceAsStream("mybatis-configs.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        System.out.println(factory);
    }

    @Test
    public void testSqlSessionConnection() {
        SqlSession session = factory.openSession();
        Connection conn = session.getConnection();
        System.out.println(conn);
    }
}

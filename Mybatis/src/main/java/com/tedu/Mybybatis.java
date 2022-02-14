package com.tedu;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Mybybatis {
//    @Test
//    public void findAll() throws IOException {
//        //1.读取mybaits核心配置文件nybatis.config
//        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
//        //2.通过配置获取SqlSessionFactory
//        SqlSessionFactory factory= new SqlSessionFactoryBuilder().build(in);
//        //3.通过工厂获取SqlSession对象（类似于connection）
//        SqlSession session =factory.openSession();
//        //4.执行sql语句（namespace+id对sql语句进行定位），返回结果
//        //增删改都用update
//        List<Emp> list= session.selectList("EmpMapper.findAll");
//        for(Emp e:list){
//            System.out.println(e);
//        }
//    }
    @Test
    public void findAll() throws IOException {
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory factory= new SqlSessionFactoryBuilder().build(in);
        SqlSession session =factory.openSession();
        EmpMapper mapper =session.getMapper(EmpMapper.class);
        List<Emp> list= mapper.findAll();
        for(Emp e:list){
            System.out.println(e);
        }
    }
}

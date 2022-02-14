package com.tedu;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestBatis {
    private static SqlSession session = null;

    static {
        try {
            //1.读取mybaits核心配置文件nybatis.config
            InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
            //2.通过配置获取SqlSessionFactory
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
            //3.通过工厂获取SqlSession对象（类似于connection）
            session = factory.openSession();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testInsert() {
        int c = session.update("EmpMapper.insert");
        //提交事物
        session.commit();
        System.out.println("影响了"+c+"行");
    }

    /*********************** #{}占位符 ***********************/
    //修改id为1的员工信息
    @Test
    public void testUpdateById(){
        //对象封装不了用map
        Emp e= new Emp();
        e.setId(1);
        e.setName("张三666");
        e.setJob("总经理");
        e.setSalary(1000);
        System.out.println(e);
        int rows = session.update("EmpMapper.updateById",e);
        session.commit();
        System.out.println(rows);

    }
    @Test
    public void testFindById(){
        //对象封装不了用map
        //只有一个变量时可以直接传
        Emp e = session.selectOne("EmpMapper.findById",1);
        session.commit();
        System.out.println(e);

    }

    /*********************** ${}占位符 ***********************/
    //     ${}占位符为不带引号的字符串进行占位
    @Test
    public void testFindAll(){
        Map m = new HashMap();
        m.put("cols","id,name");
        List<Emp> list = session.selectList("EmpMapper.findAll2",m);
        for(Emp e:list){
            System.out.println(e);
        }
    }
    @Test
    public void testLike(){
        Map m = new HashMap();
        m.put("name","刘");
        List<Emp> list = session.selectList("EmpMapper.findNameLike",m);
        for(Emp e:list){
            System.out.println(e);
        }
    }

    @Test
    public void testBySalary(){
        Map m = new HashMap();
        m.put("minSal",500);
        m.put("maxSal",6000);
        List<Emp> list = session.selectList("EmpMapper.findBySalary",m);
        for(Emp e:list){
            System.out.println(e);
        }
    }

    @Test
    public void testUpdateById2(){
       Emp emp = new Emp();
       emp.setId(1);
       emp.setName("updateById2");
        int rows = session.update("EmpMapper.updateById2",emp);
        session.commit();
        System.out.println(rows);
    }
    @Test
    public void testDeleteByIds(){
        Integer[] ids = {1,2,3};
        int rows = session.update("EmpMapper.deleteByIds",ids);
        session.commit();
        System.out.println(rows);
    }
}

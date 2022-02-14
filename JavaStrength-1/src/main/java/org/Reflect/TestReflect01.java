package org.Reflect;

import java.util.Date;

class Problem{
    private Long id;
    private String title;
    private Date createdTime;
}
public class TestReflect01 {
    public static void main(String[] args) throws Exception{
        Class<?> c = Class.forName("org.Reflect.Problem");
        //设置方法可访问
        //c.getDeclaredConstructor().setAccessible(true);
        Problem o = (Problem) c.getDeclaredConstructor().newInstance();
        System.out.println(o);
    }
}

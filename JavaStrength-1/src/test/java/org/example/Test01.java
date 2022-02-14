package org.example;

import org.junit.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Test01 {
    @Test
    public void testGenericity() throws Exception {
        List<String> list= new ArrayList<String>();
        list.add("A");
        Class<?> cls = list.getClass();
        //泛型在运行时期都会变为Object,因此参数传add函数的参数为Object.class
        Method method = cls.getMethod("add",Object.class);
        //回调
        method.invoke(list,100);
        System.out.println(list);
    }
}

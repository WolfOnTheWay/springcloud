package org.Reflect;

import java.lang.reflect.Constructor;

public class ObjectFactory {
    public static <T> T newInstance(Class<T> cls,
                                    Class<?> [] parameterTypes,
                                    Object[] args)
    throws Exception{
        //1.获取类中的构造方法对象
        Constructor<T> con = cls.getDeclaredConstructor(parameterTypes);
        //2.设置构造方法可访问
        con.setAccessible(true);
        //3.基于构造方法创建类的实例对象
        return con.newInstance(args);
    }
}

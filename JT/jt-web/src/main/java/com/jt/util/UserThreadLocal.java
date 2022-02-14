package com.jt.util;

import com.jt.pojo.User;

/**
 * @author 李志杰
 * @version 1.0
 * @description:TODO
 * @date 2022/2/3 16:34
 */
public class UserThreadLocal {
    private static ThreadLocal<User> userThreadLocal = new ThreadLocal<User>();

    public static void set(User user){
        userThreadLocal.set(user);
    }
    public static User get(){
        return userThreadLocal.get();
    }

    //防止内存泄露
    public static void remove(){
        userThreadLocal.remove();
    }
}

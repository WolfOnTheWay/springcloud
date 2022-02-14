package org.FanXing;

import java.util.ArrayList;
import java.util.List;

interface Container<T>{
    void add(T t);
    T get(int index);
}
class ArrayContainer<T> implements Container<T>{
    public void add(T t){

    }
    public T get(int index){
        return null;
    }
}
public class Test01 {
    public static void main(String[] args) {
        List<String> list1 = new ArrayList<String>();
//        List<Object> list2 = new ArrayList<String>();//错误
//        List<String> list3 = new ArrayList<Object>();//错误
        List<?> list4 = new ArrayList<Object>();
    }
}


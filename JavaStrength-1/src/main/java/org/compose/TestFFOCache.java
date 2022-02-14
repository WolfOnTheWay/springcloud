package org.compose;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

class FIFoCache{
    //借助此对象缓存数据
    private Map<String,Object> cache;
    //借助LinkedList记录key的添加顺序
    private LinkedList<String> keyList=new LinkedList<>();
    private int maxCap;
    public FIFoCache(int maxCap){
        this.maxCap=maxCap;
        this.cache = new HashMap<>(maxCap);
    }
    //负责将数据存储到cache
    public void put(String key,Object value){
        //记录key顺序
        keyList.addLast(key);
        //判断绒容器是否满了
        if (keyList.size()>this.maxCap){
            //满了则移除
            String str = keyList.removeFirst();
            cache.remove(str);
        }
        //添加新的元素
        cache.put(key,value);
    }

    @Override
    public String toString() {
        return "FIFoCache{" +
                "cache=" + cache +
                '}';
    }
}
public class TestFFOCache {
    public static void main(String[] args) {
        FIFoCache cache = new FIFoCache(2);
        cache.put("a",100);
        cache.put("b",200);
        cache.put("c",300);
        System.out.println(cache);
    }
}

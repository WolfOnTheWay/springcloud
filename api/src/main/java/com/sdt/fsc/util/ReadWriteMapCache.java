package com.sdt.fsc.util;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j
public class ReadWriteMapCache {

    private static volatile Map<String,Object> cache = new HashMap<>();
    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    /**
     *
     * @param key
     * @return
     */
    public Object get(String key){
        readWriteLock.readLock().lock();
        try {
                Object val = cache.get(key);
                //cache中存在
                return val;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //释放读锁
            readWriteLock.readLock().unlock();
        }
        return null;
    }

    public void put(String key,Object value){
        readWriteLock.writeLock().lock();
        try {
            cache.put(key,value);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            readWriteLock.writeLock().unlock();
        }
    }
}

package org.compose;


import java.util.HashMap;
import java.util.Map;

public class PerpetualCache implements Cache {
    private Map<Object,Object> cache = new HashMap<>();


    @Override
    public void putObject(Object key,Object value){
        cache.put(key,value);
    }
    @Override
    public Object getObject(Object key){
        return cache.get(key);
    }
}

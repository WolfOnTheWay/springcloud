package com.jt.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author 李志杰
 * @version 1.0
 * @description: 实现对象与JSON之间的转换
 * @date 2022/1/14 10:47
 */
public class ObjectMapperUtil {
    private  static final ObjectMapper MAPPER = new ObjectMapper();

    public static String  toJSON(Object data){
        String json;
        try {
            json = MAPPER.writeValueAsString(data);
        }catch (JsonProcessingException e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return json;
    }

    @SuppressWarnings("unchecked")
    public static <T> T toObject(String json,Class<T> target){
        T obj = null;
        try {
            obj = MAPPER.readValue(json,target);
        }catch (JsonProcessingException e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return obj;
    }
}

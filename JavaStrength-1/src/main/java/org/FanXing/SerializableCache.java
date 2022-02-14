package org.FanXing;

import org.apache.ibatis.cache.Cache;

import java.io.*;
import java.util.concurrent.locks.ReadWriteLock;

public class SerializableCache implements Cache{

    //组合
    private Cache cache;

    public SerializableCache(Cache cache){
        this.cache = cache;

    }
    @Override
    public String getId() {
        return null;
    }
    //序列化函数,参数为Serializable接口的子类
    public byte[] serializable(Serializable v) throws Exception {
        // ByteArrayOutputStream内置可扩容的byte数组
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        //典型的装饰者模式
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(v);
        //oos.flush();close时会自动刷新
        oos.close();
        //返回byte数组
        return bos.toByteArray();

    }
    @Override
    public void putObject(Object k, Object v) {
        //将v序列化,v需要强转为Serializable类型
        try {
            cache.putObject(k,serializable((Serializable)v));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object deSerializable(byte[] array) throws Exception {
        ByteArrayInputStream bis = new ByteArrayInputStream(array);
        //构建对象输入流
        ObjectInputStream ois = new ObjectInputStream(bis);
        Object obj = ois.readObject();
        ois.close();
        return obj;
    }
    @Override
    public Object getObject(Object key) {
        //基于key,获取数据
        byte[] array = (byte[])cache.getObject(key);
        //构建字节数组输入流
        try {
            Object obj =deSerializable(array);
            return obj;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Object removeObject(Object key) {

        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return null;
    }
}
//开拓视野 冲破艰险 洞悉所有 贴近生活 寻找真爱 感受彼此 去经历险恶 去抚摸柔软 去触碰神秘 去咀嚼平凡 这可能才是真正的自己

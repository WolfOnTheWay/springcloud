package com.jt.aop;

import com.jt.anno.Cache_Find;
import com.jt.util.ObjectMapperUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.ShardedJedis;

/**
 * @author 李志杰
 * @version 1.0
 * @description:TODO
 * @date 2022/1/14 15:24
 */
@Aspect//标识切面
@Component
public class CacheAspect {
//    @Autowired
//    private Jedis jedis;
    //required=false，当用户使用时才进行注入
//    @Autowired(required = false)
//    private ShardedJedis jedis;

    @Autowired
    private JedisCluster jedisCluster;

    @Around("@annotation(cache_find)")
    public Object around(ProceedingJoinPoint joinPoint, Cache_Find cache_find) {
        String key = getKey(joinPoint, cache_find);
        String result = jedisCluster.get(key);
        Object data;
        try {
            if (StringUtils.isEmpty(result)) {
                //目标方法继续执
                data = joinPoint.proceed();
                String json = ObjectMapperUtil.toJSON(data);
                if(cache_find.senconds()>0){
                    jedisCluster.setex(key,cache_find.senconds(),json);
                }else{
                    jedisCluster.set(key,json);
                }
                System.out.println("AOP查询数据库");
            }else{
                Class returnClass = getReturnClass(joinPoint);
                data = ObjectMapperUtil.toObject(result,returnClass);
                System.out.println("AOP查询缓存");
            }
        } catch (Throwable e) {
            e.printStackTrace();
            return new RuntimeException( e.getMessage());
        }
        return data;
    }

    private Class getReturnClass(ProceedingJoinPoint joinPoint) {
        MethodSignature ms = (MethodSignature)joinPoint.getSignature();
        return ms.getReturnType();
    }

    private String getKey(ProceedingJoinPoint joinPoint, Cache_Find cache_find) {
        String key = cache_find.key();
        if (StringUtils.isEmpty(key)) {
            //key自动生成
            String className =
                    joinPoint.getSignature().getDeclaringTypeName();
            String methodName =
                    joinPoint.getSignature().getName();
            if (joinPoint.getArgs().length > 0)
                //拼接第一个参数
                key = className + "." + methodName + "::"
                        + joinPoint.getArgs()[0];
            else
                key = className + "." + methodName;
        }
        return key;
    }


}

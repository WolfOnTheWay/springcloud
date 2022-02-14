package com.jt.config;

import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author 李志杰
 * @version 1.0
 * @description:TODO
 * @date 2022/1/14 14:03
 */
@Configuration
@PropertySource("classpath:/properties/redis.properties")
public class RedisConfig {
    @Value("${redis.nodes}")
    private String nodes;


    @Bean
    public JedisCluster jedisCluster(){
        Set<HostAndPort> set = new HashSet<HostAndPort>();
        String[] arrNodes = nodes.split(",");
        for(String node:arrNodes){
            String host = node.split(":")[0];
            int port = Integer.parseInt(node.split(":")[1]);
            HostAndPort hostAndPort = new HostAndPort(host,port);
            set.add(hostAndPort);
        }
        return new JedisCluster(set);
    }
//    @Bean
//    public ShardedJedis shardedJedis(){
//        List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
//        String[] arryNodes = nodes.split(",");
//        for(String node:arryNodes){
//            String host = node.split(":")[0];
//            int port = Integer.parseInt(node.split(":")[1]);
//            JedisShardInfo info = new JedisShardInfo(host,port);
//            shards.add(info);
//        }
//        return new ShardedJedis(shards);
//    }

//    @Value("${redis.host}")
//    private String host;
//    @Value("${redis.port}")
//    private Integer port;
//    @Bean
//    @Lazy //懒加载
//    public Jedis jedis(){
//        return new Jedis(host,port);
//    }
}

package com.bjpowernode.commons;

import com.bjpowernode.constant.Constant;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;

/**
 *基础的入口类
 */

public class BaseApplication implements CommandLineRunner {
    @Value(value = "${dlyk.jwt.secret}")
    private String jwtSecret;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void run(String... args) throws Exception {
        Constant.JWT_SECRET = jwtSecret;

        //redisTemplate的key和value的序列化方式
        redisTemplate.setKeySerializer(redisTemplate.getStringSerializer());
        redisTemplate.setValueSerializer(redisTemplate.getStringSerializer());
    }
}

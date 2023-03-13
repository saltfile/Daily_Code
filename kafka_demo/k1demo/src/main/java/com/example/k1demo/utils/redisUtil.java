package com.example.k1demo.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class redisUtil {

    @Resource
    private RedisTemplate<String, Integer> redisTemplate;

    @Resource
    private RedisTemplate<Integer,String> UserTemplate;

    public Integer Gget(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    public void Gset(String k,Integer v,long expire){
        redisTemplate.opsForValue().set(k, v, expire, TimeUnit.SECONDS);
    }

    //费原子操作




}

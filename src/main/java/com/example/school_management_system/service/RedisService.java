package com.example.school_management_system.service;

import com.example.school_management_system.dto.CreateUser;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {
    //vairabels Block
    private final RedisTemplate<String,String> redisTemplate;

    //Constroctor
    public RedisService(RedisTemplate<String,String> redisTemplate){
       this.redisTemplate=redisTemplate;
    }


    //Mothods
    public void saveKeyValue(String key,String value){
       redisTemplate.opsForValue().set(key,value);
    }
    public String getValue(String key){
     return redisTemplate.opsForValue().get(key);
    }
    public void delete(String key) {
        redisTemplate.delete(key);
    }
    public boolean existsKey(String key){
     return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }
    public void blacklistToken(String token,long expireRationTime){
        redisTemplate.opsForValue().set("blacklist:" + token,
                "true",expireRationTime, TimeUnit.SECONDS);
    }
    public boolean isBlacklisted(String token) {

        return Boolean.TRUE.equals(
                redisTemplate.hasKey("blacklist:" + token)
        );
    }
    public void save(String key,String value,long expireRationTime){
        redisTemplate.opsForValue().set(key,value,expireRationTime,TimeUnit.SECONDS);
    }


}


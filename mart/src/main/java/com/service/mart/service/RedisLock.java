package com.service.mart.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@Slf4j
public class RedisLock {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * Apply lock.
     * @param key
     * @param value current time + timeout
     * @return
     */
    public boolean lock(String key, String value){
        if (redisTemplate.opsForValue().setIfAbsent(key, value)){ // if nx can be set(locked)
            return true;
        }

        // assume: current value=A, value=B.
        String currentValue = redisTemplate.opsForValue().get(key);
        // if the lock has been outdated (prevent deadlock when errors happen)
        if (!StringUtils.isEmpty(currentValue) &&
                Long.parseLong(currentValue) < System.currentTimeMillis()){
            // get the lock's old value and set it to new value
            String oldValue = redisTemplate.opsForValue().getAndSet(key, value);
            // if old value equals current value, lock
            // when there are two or more threads, only one thread will get the lock
            if (!StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue)){
                return true;
            }

        }

        return false;
    }

    /**
     * Unlock.
     * @param key
     * @param value
     * @return
     */

    public boolean unlock(String key, String value){
        try{
            String currentValue = redisTemplate.opsForValue().get(key);
            // if currentValue equals new value, unlock.
            if (!StringUtils.isEmpty(currentValue) && currentValue.equals(value)){
                redisTemplate.opsForValue().getOperations().delete(key);
                return true;
            }
        } catch (Exception e){
            log.error("[Redis Distributed Lock] Unlock error.");
        }

        return false;
    }
}

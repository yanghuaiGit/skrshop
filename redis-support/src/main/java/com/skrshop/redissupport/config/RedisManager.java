package com.skrshop.redissupport.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author huaiyang
 * @version 1.0.0
 * @date 2020/1/14
 */
@Slf4j
@Component
public class RedisManager {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public Boolean exists(String key) {
        return this.redisTemplate.hasKey(key);
    }

    public Boolean set(String key, Object value) {
        try {
            this.redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception var4) {
            log.info("set写入Redis缓存失败{}", var4.toString(), var4);
            return false;
        }
    }

    public Boolean set(String key, Object value, long expire) {
        try {
            this.redisTemplate.opsForValue().set(key, value);
            this.redisTemplate.expire(key, expire, TimeUnit.SECONDS);
            return true;
        } catch (Exception var6) {
            log.info("set写入Redis缓存失败，{}", var6.toString(), var6);
            return false;
        }
    }

    /**
     * key 设置过期时间
     *
     * @param key    键
     * @param expire 过期时间
     */
    public Boolean expire(String key, long expire) {
        return this.expire(key, expire, TimeUnit.SECONDS);
    }

    public Boolean expire(String key, long expire, TimeUnit timeUnit) {
        return this.redisTemplate.expire(key, expire, timeUnit);
    }

    public Object get(String key) {
        return this.redisTemplate.opsForValue().get(key);
    }

    public Boolean remove(String key) {
        if (this.exists(key)) {
            return this.redisTemplate.delete(key);
        } else {
            return true;
        }
    }

    public void removes(String... keys) {
        String[] var2 = keys;
        int var3 = keys.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            String key = var2[var4];
            this.remove(key);
        }

    }

    public Boolean removes(Collection<String> keys) {
        if (CollectionUtils.isEmpty(keys)) {
            return true;
        }
        Long delete = this.redisTemplate.delete(keys);
        if (Objects.isNull(delete)) {
            return false;
        }
        return keys.size() == delete.intValue();
    }

    public void removePattern(String pattern) {
        Set<String> keys = this.redisTemplate.keys(pattern);
        if (keys != null && keys.size() > 0) {
            this.redisTemplate.delete(keys);
        }
    }

    public Boolean lpush(String key, Object value) {
        try {
            this.redisTemplate.opsForList().leftPush(key, value);
            return true;
        } catch (Exception var4) {
            log.info("lpush写入Redis缓存失败,{}", var4.toString(), var4);
            return false;
        }
    }

    public List<Object> range(String key, int start, int end) {
        return this.redisTemplate.opsForList().range(key, (long) start, (long) end);
    }

    public Boolean rpop(String key) {
        try {
            this.redisTemplate.opsForList().rightPop(key);
            return true;
        } catch (Exception var3) {
            log.info("rpop移除Redis缓存失败。" + var3.toString());
            log.error("rpop移除Redis缓存失败。", var3);
            return false;
        }
    }

    public void remove(String key, int index, String value) {
        ListOperations<String, Object> operations = this.redisTemplate.opsForList();
        operations.remove(key, (long) index, value);
    }

    public Boolean hset(String key, String hashKey, Object hashValue) {
        try {
            HashOperations<String, String, Object> hashOper = this.redisTemplate.opsForHash();
            hashOper.put(key, hashKey, hashValue);
            return true;
        } catch (Exception var5) {
            log.info("hset写入Redis缓存失败,{}", var5.toString(), var5);
            return false;
        }
    }

    public Boolean hashKeyExists(String key, String hashKey) {
        HashOperations<String, String, Object> hashOper = this.redisTemplate.opsForHash();
        Boolean b = hashOper.hasKey(key, hashKey);
        return b == null ? false : b;
    }

    public Object hget(String key, String hashKey) {
        HashOperations<String, String, Object> hashOper = this.redisTemplate.opsForHash();
        return hashOper.get(key, hashKey);
    }

    public Set<String> hkeys(String key) {
        HashOperations<String, String, Object> hashOper = this.redisTemplate.opsForHash();
        return hashOper.keys(key);
    }

    public List<Object> hValues(String key) {
        HashOperations<String, String, Object> hashOper = this.redisTemplate.opsForHash();
        return hashOper.values(key);
    }

    public Map<String, Object> hMap(String key) {
        HashOperations<String, String, Object> hashOper = this.redisTemplate.opsForHash();
        return hashOper.entries(key);
    }

    public boolean hdel(String key, String hashKey) {
        try {
            HashOperations<String, String, Object> hashOper = this.redisTemplate.opsForHash();
            hashOper.delete(key, new Object[]{hashKey});
            return true;
        } catch (Exception var4) {
            log.info("hdel删除Redis键值对失败,{}", var4.toString(), var4);
            return false;
        }
    }

    /**
     * 返回的是增加后的值
     *
     * @param key 递增key
     * @return 递增结果
     */
    public Long incr(String key) {
        return this.incr(key, 1);
    }

    public Long incr(String key, long range) {
        return this.redisTemplate.opsForValue().increment(key, range);
    }

}

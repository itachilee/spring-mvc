package com.app.mvc.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import javax.annotation.Resource;

/**
 * Created by jimin on 16/6/24.
 */
@Service("redisPool")
@Slf4j
public class RedisPool {

    /**
     * if use redis, should check these files:
     * (close injecting code, for no need to start redis-server every time)
     *
     * applicationContext.xml
     * RedisPool.java
     * SysCacheService.java
     */
    @Resource(name = "shardedJedisPool")
    private ShardedJedisPool shardedJedisPool;

    public ShardedJedis instance() {
        return shardedJedisPool.getResource();
    }

    public void safeClose(ShardedJedis shardedJedis) {
        try {
            if (shardedJedis != null) {
                shardedJedis.close();
            }
        } catch (Exception e) {
            log.error("return redis resource exception", e);
        }
    }
}

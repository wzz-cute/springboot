package com.wzz.redis.controller.lock;

import com.wzz.gulimall.common.utils.R;
import com.wzz.redis.service.RedisLockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis-lock")
public class RedisLockController {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisLockService redisLockService;

    /**
     * 使用redis实现分布式锁
     *
     * @return
     */
    @RequestMapping("/redisLock")
    public Object getRedisLock() {
        R r = redisLockService.getDataByMyPublicLock();
        return r;
    }

    /**
     * 使用redis实现分布式锁
     *
     * @return
     */
    @RequestMapping("/getReentrantLock")
    public Object getReentrantLock() {
        R r = redisLockService.getReentrantLock();
        return r;
    }

    /**
     * 使用redisson读写锁
     *
     * @return
     */
    @RequestMapping("/getWriteLock")
    public Object getWriteLock() {
        String r = redisLockService.getWriteLock();
        return r;
    }

    /**
     * 使用redisson读写锁
     *
     * @return
     */
    @RequestMapping("/getReadLock")
    public Object getReadLock() {
        String r = redisLockService.getReadLock();
        return r;
    }

    /**
     * 使用redisson实现计数器
     *
     * @return
     */
    @RequestMapping("/getCountDownLatch")
    public Object getCountDownLatch() {
        String r = redisLockService.getCountDownLatch();
        return r;
    }

    /**
     * 使用redisson实现计数器
     *
     * @return
     */
    @RequestMapping("/getCountDownLatchResult")
    public Object getCountDownLatchResult() {
        String r = redisLockService.getCountDownLatchResult();
        return r;
    }

    /**
     * 使用redisson信号量
     *
     * @return
     */
    @RequestMapping("/getSemaphoreLock")
    public Object getSemaphoreLock() {
        String r = redisLockService.getSemaphoreLock();
        return r;
    }

    /**
     * 使用redisson信号量
     *
     * @return
     */
    @RequestMapping("/getSemaphoreLockResult")
    public Object getSemaphoreLockResult() {
        String r = redisLockService.getSemaphoreLockResult();
        return r;
    }


}

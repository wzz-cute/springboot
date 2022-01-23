package com.wzz.redis.service.impl;

import com.wzz.gulimall.common.utils.R;
import com.wzz.redis.entity.PmsCategoryEntity;
import com.wzz.redis.service.PmsCategoryService;
import com.wzz.redis.service.RedisLockService;
import org.redisson.api.*;
import org.redisson.client.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service("redisLockService")
public class RedisLockServiceImpl implements RedisLockService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private RedissonClient redission;

    @Autowired
    private PmsCategoryService pmsCategoryService;


    /**
     * 使用redis nx 机制  如果没有键值对就添加
     * 隐患：如果出了异常，或者挂了，否则一直等待，或者崩掉
     *
     * @return
     */
    @Override
    public R getDataByMyPublicLock() {
//        Boolean aBoolean = redisTemplate.opsForValue().setIfAbsent("pmsLock", "aaa");

        //使用原子命令占锁 上锁与过期一起实现 相对于 ex nx一起

        String uuid = UUID.randomUUID().toString();
        Boolean lock = redisTemplate.opsForValue().setIfAbsent("lock", uuid, 300, TimeUnit.SECONDS);
        if (lock) {
            System.out.println("获取分布式锁成功");
//                redisTemplate.expire("pmsLock", 30, TimeUnit.SECONDS);
            try {
                List<PmsCategoryEntity> data = pmsCategoryService.getData();
                return new R().put("ok", data);
            } finally {
                //使用lua脚本进行删除，保证判断值和删除值一致性
                String script = "if redis.call('get',KEYS[1]) == ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end";
                Long lock1 = redisTemplate.execute(new DefaultRedisScript<Long>(script, Long.class),
                        Arrays.asList("lock"), uuid);
            }
//                String value = redisTemplate.opsForValue().get("lock");
//                if (value.equals(lock)){
//                    redisTemplate.delete("pmsLock");
//                }
        } else {
            System.out.println("获取分布式锁失败，等待重试");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            getDataByMyPublicLock();
            return new R().put("full", "失败");
        }

    }

    /**
     * 如果其中一个服务进行业务中  突然短掉 redisson没有释放锁 也不会造成死锁，走完默认时间即可放锁
     * 如果自己设置了业务时间 就不会有续航功能
     *
     * @return
     */
    @Override
    public R getReentrantLock() {
        RLock lock = redission.getLock("my-lock");
//        RLock lock = redission.getFairLock("my-lock"); //公平锁
//        lock.lock();//阻塞式等待 默认30秒钟时间  看门狗机制
        lock.lock(10, TimeUnit.SECONDS);//阻塞式等待 默认30秒钟时间  看门狗机制
        try {
            System.out.println("执行业务  加锁成功" + Thread.currentThread().getId());
            Thread.sleep(30000);
        } catch (Exception e) {

        } finally {
            System.out.println("释放锁" + Thread.currentThread().getId());
            lock.unlock();
        }

        return new R().put("data", "hello");
    }

    @Override
    public String getWriteLock() {
        String s = UUID.randomUUID().toString();
        RReadWriteLock readWriteLock = redission.getReadWriteLock("write-lock");
        RLock rLock = readWriteLock.writeLock();
        try {
            rLock.lock();
            Thread.sleep(30000);
            redisTemplate.opsForValue().set("writeValue", s);
        } catch (InterruptedException e) {

        } finally {
            rLock.unlock();
        }

        return s;
    }

    @Override
    public String getReadLock() {
        RReadWriteLock readWriteLock = redission.getReadWriteLock("read-lock");
        RLock rLock = readWriteLock.readLock();
        String writeValue = null;
        try {
            rLock.lock();
            Thread.sleep(30000);
            writeValue = redisTemplate.opsForValue().get("writeValue");
        } catch (InterruptedException e) {

        } finally {
            rLock.unlock();
        }

        return writeValue;
    }

    @Override
    public String getCountDownLatch() {
        RCountDownLatch countDownLatch = redission.getCountDownLatch("count-lock");
        System.out.println("开始等待：5");
        try {
            countDownLatch.trySetCount(5);
            countDownLatch.await();
        } catch (InterruptedException e) {

        }
        return "等待完毕，执行";
    }

    @Override
    public String getCountDownLatchResult() {
        RCountDownLatch countDownLatch = redission.getCountDownLatch("count-lock");
        countDownLatch.countDown();
        return "走了一个班";
    }

    @Override
    public String getSemaphoreLock() {
        RSemaphore park = redission.getSemaphore("park");
        boolean b = false;
        try {
//            park.acquire(5);
            b = park.tryAcquire(10);

        } catch (Exception e) {

        }
        return "ok==>" + b;
    }

    @Override
    public String getSemaphoreLockResult() {
        RSemaphore park = redission.getSemaphore("park");
        park.release();
        return "ok";
    }
}

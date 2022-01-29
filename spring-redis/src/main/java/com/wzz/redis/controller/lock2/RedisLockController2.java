package com.wzz.redis.controller.lock2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/redissonlock1")
public class RedisLockController2 {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 如果没有抢到锁 那么久递归 直到抢到位置
     *
     * @return
     */
    @RequestMapping("/getRedisLock")
    public String getRedisLock() {
        String uuid = UUID.randomUUID().toString();
        /**  一般操作
         *   1.先看redis有没有这把锁
         *   2.如果没有 就去 redis里面添加锁  断电 运行不了
         *   3.上锁执行业务
         *   解决：set锁  有没有锁  赋值过期时间 一起完成  EX
         *
         *
         *   4.业务执行完毕 解锁  删除锁
         *
         */
        System.out.println("用户进入" + Thread.currentThread().getId());
        Boolean lock = redisTemplate.opsForValue().setIfAbsent("my-lock", uuid,
                30, TimeUnit.SECONDS);
        if (lock) {
            try {
                System.out.println("执行业务" + Thread.currentThread().getId());
                Thread.sleep(30000);//模拟进行业务中
                return "业务执行完毕" + Thread.currentThread().getId();
            } catch (Exception e) {
            } finally {
                System.out.println("删除锁" + Thread.currentThread().getId());
                redisTemplate.delete("my-lock");
            }
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return getRedisLock();
    }


}
















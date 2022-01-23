package com.wzz.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class SpringRedisApplicationTests {

    //string lis hash set zset
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void redisString(){
        ValueOperations forValue = redisTemplate.opsForValue();
        forValue.set("aaa","1");
    }

}

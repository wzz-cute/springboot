package com.wzz.redis.controller.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("String-Redis")
public class StringRedisController {

    //string lis hash set zset
    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/setRedisString")
    public void setredisString(String key,String Value){
        ValueOperations<String ,String> forValue = redisTemplate.opsForValue();
        //存入String
        forValue.set("giao:"+key,Value);

        //存入String 定时
        forValue.set(key,Value,60, TimeUnit.SECONDS);
        return ;
    }

    @RequestMapping("/getRedisString")
    public String getredisString(String key){
        ValueOperations<String ,String> forValue = redisTemplate.opsForValue();

        //根据key获取Value
        String s = forValue.get(key);
        System.out.println(s);
        return s;
    }

    @RequestMapping("/delRedisString")
    public void delredisString(String key){
        ValueOperations<String ,String> forValue = redisTemplate.opsForValue();
        forValue.append(key,"111");

        //根据key删除键值对  对象可以是集合
//        redisTemplate.delete(key);
    }

}

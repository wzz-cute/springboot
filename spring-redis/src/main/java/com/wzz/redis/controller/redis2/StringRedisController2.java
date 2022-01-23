package com.wzz.redis.controller.redis2;

import com.wzz.redis.service.PmsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/StrRedis")
public class StringRedisController2 {
    @Autowired
    private PmsCategoryService pmsCategoryService;

    //string list hash set zset
    //String list 基本信息  key:xxx  value:时间戳
    @Autowired
    private RedisTemplate redisTemplate;


    //添加时 设置过期时间 过期时间可以
    @RequestMapping("/addStr")
    public void addStringRedis(String key,String value){
        ValueOperations<String,Object> str = redisTemplate.opsForValue();

        str.set(key,value);
        str.set(key+"1",value,60, TimeUnit.SECONDS);
        str.append(key,"+abc");

        str.set("session","wzz");
        redisTemplate.expire("session",60,TimeUnit.DAYS);

        //可以根据集合方式 或 单个方式删除
//        redisTemplate.delete()
    }

    @RequestMapping("/getStr")
    public void getStringRedis(String key,String value){
        ValueOperations<String,Object> str = redisTemplate.opsForValue();

        System.out.println(str.get("name"));
        System.out.println(str.get("session"));
    }



}

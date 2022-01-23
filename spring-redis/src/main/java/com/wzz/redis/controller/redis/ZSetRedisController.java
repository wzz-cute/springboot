package com.wzz.redis.controller.redis;

import com.alibaba.fastjson.JSON;
import com.wzz.redis.entity.PmsCategoryEntity;
import com.wzz.redis.service.PmsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

//https://www.cnblogs.com/ants_double/p/14917037.html
@RestController
@RequestMapping("ZSet-Redis")
public class ZSetRedisController {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private PmsCategoryService pmsCategoryService;

    @RequestMapping("setZSETData")
    public void setListRedis() {
        ZSetOperations<String,String> zSetOperations = redisTemplate.opsForZSet();

        ZSetOperations<String,List<PmsCategoryEntity>> zSetOperations1 = redisTemplate.opsForZSet();

        zSetOperations.add("ZSET","aaa",4);
        zSetOperations.add("ZSET","54",5);
        zSetOperations.add("ZSET","352",0);
        zSetOperations.add("ZSET","46",1);

        List<PmsCategoryEntity> infoByParms = pmsCategoryService.getData();

        System.out.println(redisTemplate.hasKey("setValue"));
        System.out.println(redisTemplate.hasKey("pmsData"));
    }

    @RequestMapping("/getZSET")
    public void getHash() {
        SetOperations<String,String> setOperations = redisTemplate.opsForSet();
        SetOperations<String,List<PmsCategoryEntity>> pms = redisTemplate.opsForSet();

        Set<String> setValue = setOperations.members("setValue");
        System.out.println(JSON.toJSONString(setValue));

        Set<List<PmsCategoryEntity>> pmsData = pms.members("pmsData");
        System.out.println(JSON.toJSONString(pmsData));
    }



}

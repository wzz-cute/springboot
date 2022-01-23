package com.wzz.redis.controller.redis;

import com.alibaba.fastjson.JSON;
import com.wzz.gulimall.common.utils.R;
import com.wzz.redis.entity.PmsCategoryEntity;
import com.wzz.redis.service.PmsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

//https://www.cnblogs.com/ants_double/p/14917037.html
@RestController
@RequestMapping("Set-Redis")
public class SetRedisController {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private PmsCategoryService pmsCategoryService;

    @RequestMapping("setSETData")
    public void setListRedis() {
        SetOperations<String,String> setOperations = redisTemplate.opsForSet();

        SetOperations<String,List<PmsCategoryEntity>> pms = redisTemplate.opsForSet();

        setOperations.add("setValue","1","50","bc","42344","%^&",")(");

        List<PmsCategoryEntity> infoByParms = pmsCategoryService.getData();
        pms.add("pmsData",infoByParms);

        System.out.println(redisTemplate.hasKey("setValue"));
        System.out.println(redisTemplate.hasKey("pmsData"));
    }

    @RequestMapping("/getSET")
    public void getHash() {
        SetOperations<String,String> setOperations = redisTemplate.opsForSet();
        SetOperations<String,List<PmsCategoryEntity>> pms = redisTemplate.opsForSet();

        Set<String> setValue = setOperations.members("setValue");
        System.out.println(JSON.toJSONString(setValue));

        Set<List<PmsCategoryEntity>> pmsData = pms.members("pmsData");
        System.out.println(JSON.toJSONString(pmsData));
    }



}

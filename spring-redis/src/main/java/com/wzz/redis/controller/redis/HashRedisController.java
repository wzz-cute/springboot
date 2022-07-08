package com.wzz.redis.controller.redis;

import com.alibaba.fastjson.JSON;
import com.wzz.util.result.R;
import com.wzz.redis.entity.PmsCategoryEntity;
import com.wzz.redis.service.PmsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//https://www.cnblogs.com/ants_double/p/14917037.html
@RestController
@RequestMapping("Hash-Redis")
public class HashRedisController {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private PmsCategoryService pmsCategoryService;

    @RequestMapping("setHashData")
    public void setListRedis() {
        HashOperations<String,String,List<PmsCategoryEntity>> hashOperations = redisTemplate.opsForHash();
        List<PmsCategoryEntity> infoByParms = pmsCategoryService.getData();

        hashOperations.put("PMSINFO", "DATA", infoByParms);

        System.out.println(redisTemplate.hasKey("PMSINFO"));
    }

    @RequestMapping("/catchRedis")
    public R catchRedis() {
        List<PmsCategoryEntity> infoByParms = pmsCategoryService.getData();
        return new R().put("data", infoByParms);
    }

    @RequestMapping("/getHash")
    public void getHash() {
        HashOperations<String,String,List<PmsCategoryEntity>> hashOperations = redisTemplate.opsForHash();

        List<PmsCategoryEntity> pmsCategoryEntities = hashOperations.get("PMSINFO", "DATA");
        System.out.println(JSON.toJSONString(pmsCategoryEntities));
    }



}

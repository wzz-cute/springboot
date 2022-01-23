package com.wzz.redis.controller.redis;

import com.alibaba.fastjson.JSON;
import com.wzz.redis.entity.PmsCategoryEntity;
import com.wzz.redis.service.PmsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("List-Redis")
public class ListRedisController {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private PmsCategoryService pmsCategoryService;

    @RequestMapping("setListData")
    public void setListRedis(int index) {
        ListOperations<String, List<PmsCategoryEntity>> listOperations = redisTemplate.opsForList();
        Map<String, Object> parse = new HashMap<>();
        parse.put("page", 1);
        parse.put("pageSize", 10);

        List<PmsCategoryEntity> infoByParms = pmsCategoryService.getInfoByParms(parse);

        Long pms = listOperations.leftPush("pms", infoByParms);
//        listOperations.set("pms", index, infoByParms);

        parse.put("page", 3);
        parse.put("pageSize", 5);

        List<PmsCategoryEntity> infoByParms2 = pmsCategoryService.getInfoByParms(parse);
        listOperations.leftPush("pms", infoByParms2);


        listOperations.set("pms", 1, infoByParms2);
    }

    @RequestMapping("getListData")
    public void getListRedis(String key) {
        ListOperations<String, List<PmsCategoryEntity>> listOperations = redisTemplate.opsForList();

        List<PmsCategoryEntity> data = listOperations.index(key, 0);
        List<PmsCategoryEntity> pms = listOperations.leftPop(key);
        List<PmsCategoryEntity> pms1 = listOperations.rightPop(key);
        List<List<PmsCategoryEntity>> range = listOperations.range(key, 1, 3);
        redisTemplate.expire("pms",60, TimeUnit.SECONDS);


        System.out.println("leftPop:" + JSON.toJSONString(pms));
        System.out.println("rightPop:" + JSON.toJSONString(pms1));
        System.out.println("data:" + JSON.toJSONString(data));
    }


}

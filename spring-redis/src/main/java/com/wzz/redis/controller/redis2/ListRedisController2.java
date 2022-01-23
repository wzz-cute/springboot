package com.wzz.redis.controller.redis2;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.wzz.redis.entity.PmsCategoryEntity;
import com.wzz.redis.service.PmsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/listRedis")
public class ListRedisController2 {
    @Autowired
    private PmsCategoryService pmsCategoryService;

    @Autowired
    private RedisTemplate redisTemplate;


    //添加时 设置过期时间 过期时间可以
    @RequestMapping("/addList")
    public void addListRedis() {
        ListOperations<String, Object> list = redisTemplate.opsForList();

        Map<String, Object> parse = new HashMap<>();
        parse.put("page", 1);
        parse.put("pageSize", 5);

        List<PmsCategoryEntity> infoByParms = pmsCategoryService.getInfoByParms(parse);

        //从左边插入
        list.leftPush("infoByParms", infoByParms);


        parse.put("page", 3);
        parse.put("pageSize", 5);
        List<PmsCategoryEntity> infoByParms2 = pmsCategoryService.getInfoByParms(parse);

        //从右边插入
        list.rightPush("infoByParms", infoByParms2);


        parse.put("page", 7);
        parse.put("pageSize", 5);

        List<PmsCategoryEntity> infoByParms3 = pmsCategoryService.getInfoByParms(parse);

        //从左边插入
        list.leftPush("infoByParms", infoByParms3);


        parse.put("page", 10);
        parse.put("pageSize", 5);

        List<PmsCategoryEntity> infoByParms4 = pmsCategoryService.getInfoByParms(parse);

        //从左边插入
        list.leftPush("infoByParms", infoByParms4);
    }

    @RequestMapping("/getList")
    public void getStringRedis() {
        ListOperations<String, Object> list = redisTemplate.opsForList();

        List<Object> infoByParms = list.range("infoByParms", 2, 4);
        System.out.println(JSON.toJSONString(infoByParms));

        Object infoByParms1 = list.index("infoByParms", 2);
        System.out.println(JSON.toJSONString(infoByParms1));

        System.out.println(JSON.toJSONString(list.leftPop("infoByParms")));
        System.out.println(JSON.toJSONString(list.leftPop("infoByParms")));
        System.out.println(JSON.toJSONString(list.leftPop("infoByParms")));
    }


}

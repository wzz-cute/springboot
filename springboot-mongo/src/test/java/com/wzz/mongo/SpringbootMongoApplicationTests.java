package com.wzz.mongo;

import com.alibaba.fastjson.JSONObject;
import com.wzz.mongo.entity.Xiyou;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
class SpringbootMongoApplicationTests {

    @Autowired
    private MongoTemplate mongoTemplate;


    @Test
    public void findAll(){
        List<Xiyou> all = mongoTemplate.findAll(Xiyou.class);
        for (Xiyou xiyou : all) {
            System.out.println(JSONObject.toJSONString(xiyou));
        }
//        System.out.println(JSONObject.toJSONString(all));
    }

}

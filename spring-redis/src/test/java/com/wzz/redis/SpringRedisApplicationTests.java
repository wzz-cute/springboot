package com.wzz.redis;

import com.wzz.redis.entity.User;
import com.wzz.redis.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
class SpringRedisApplicationTests {

    //string lis hash set zset
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserService userService;

    @Test
    public void redisString() {
        ValueOperations forValue = redisTemplate.opsForValue();
        forValue.set("aaa", "1");
    }

    @Test
    public void updateUser() {
        //一般我们不用 int long  Integer Long
        List<Long> uids = new ArrayList<>();
        uids.add(1l);
        uids.add(2l);
        uids.add(3l);
        User user = new User();
        user.setSex("女");
        user.setUids(uids);

        Integer result = userService.modifyUser(user);
        System.out.println(result);
    }

    @Test
    public void delUser() {
        //一般我们不用 int long  Integer Long
        List<Long> uids = new ArrayList<>();
        uids.add(5l);
        uids.add(4l);
        uids.add(3l);

        for (Long uid : uids) {
            System.out.println(uid);
        }


        User user = new User();
//        user.setUids(uids);
        user.setUid(1l);

        Integer result = userService.delUser(user);
        System.out.println(result);
    }


}

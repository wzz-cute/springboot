package com.wzz.redis.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @author ls
 * @date 2021/1/6 18:53
 */
@Configuration
public class MyRedissonConfig {

    /**
     * 所有堆redisson的操作都是通过RedissonClient来完成
     * @return
     * @throws IOException
     */
    @Bean(destroyMethod="shutdown")
    public RedissonClient redisson() throws IOException {
        //创建配置对象
        Config config = new Config();
        //设置redis地址
        config.useSingleServer().setAddress("redis://192.168.79.131:6379");
        //根据Config创建出RedissonClient 示例
        RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;
    }
}

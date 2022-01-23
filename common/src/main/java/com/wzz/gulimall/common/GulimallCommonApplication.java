package com.wzz.gulimall.common;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wzz.gulimall.common.test")
public class GulimallCommonApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimallCommonApplication.class, args);
    }

}

package com.wzz.juc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SpringbootJucApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJucApplication.class, args);
    }

}

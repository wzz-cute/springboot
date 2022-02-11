package com.wzz.mongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SpringbootMongoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMongoApplication.class, args);
    }

}

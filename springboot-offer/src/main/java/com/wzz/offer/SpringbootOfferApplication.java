package com.wzz.offer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SpringbootOfferApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootOfferApplication.class, args);
    }

}

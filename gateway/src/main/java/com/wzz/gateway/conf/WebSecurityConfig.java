package com.wzz.gateway.conf;

//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//
///**
// * @author menglinjie
// * @date Created in 2020/6/22 12:01
// * @description: 注意：webflux环境下要生效必须用注解@EnableWebFluxSecurity使其生效
// * cloud gateway采用的webflux技术（此处与web不同）
// */
//@Configuration
//@EnableWebFluxSecurity
//public class WebSecurityConfig {
//
//    @Bean
//    SecurityWebFilterChain webFluxSecurityFilterChain(ServerHttpSecurity http) throws Exception {
//
//        http.authorizeExchange()
//                .anyExchange()
//                .permitAll();
//
//        // 一些配置
//        http
//                .csrf().disable()
//                .httpBasic().disable()
//                .logout().disable()
//                .formLogin().disable();
//        return http.build();
//    }
//
//}

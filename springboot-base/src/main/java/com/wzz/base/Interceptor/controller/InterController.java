package com.wzz.base.Interceptor.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/base/api")
public class InterController {
    @GetMapping("/hello")
    public String hello() {
        return "hello,Interceptor";
    }
}

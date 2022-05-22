package com.wzz.redis.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @RequestMapping("/")
    public String index(){
        return "/<h1>欢迎来到index 我充当一下项目欢迎页</h1>";
    }


    @RequestMapping("/toIndex")
    public String toIndex(){
        return "<h1>欢迎来到index 我充当一下项目欢迎页</h1>";
    }
}

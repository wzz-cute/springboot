package com.wzz.base.aop.controller;

import com.alibaba.fastjson.JSON;
import com.wzz.base.aop.an.LogTrack;
import com.wzz.base.aop.entity.Student;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/xxx")
public class IndexController {

    @LogTrack("啦啦啦")
    @GetMapping("/hello")
    public String hello(String name, Integer i, Integer id) {
        return "hello " + name + "!";
    }

    //    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @LogTrack("login")
    @PostMapping(value = "/login")
    public String abc(@RequestBody Student student) {
        return JSON.toJSONString(student);
    }

}

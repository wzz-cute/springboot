package com.wzz.base.filter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stu")
public class StudentController {
    @GetMapping("hello")
    public String hello(){
        return "hello world";
    }


    @GetMapping("hello2")
    public String hello2(){
        return "hello2 world";
    }


    @GetMapping("hello33")
    public String hello33(){
        return "hello33 world";
    }


    @GetMapping("hello4")
    public String hello4(){
        return "hello4 world";
    }

    @GetMapping("login")
    public String login(){
        return "login";
    }
}

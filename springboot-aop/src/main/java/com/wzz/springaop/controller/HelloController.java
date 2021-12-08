package com.wzz.springaop.controller;

import com.wzz.springaop.an.LogTrack;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RestController
public class HelloController {

    @LogTrack(value = "sayHello接口")
    @GetMapping("/sayHello")
    public String sayHello(@RequestParam("name") String name){
        return name+",hello!";
    }

    @GetMapping("/getInfo")
    public String getInfo(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        return request.getParameter("info");
    }

}

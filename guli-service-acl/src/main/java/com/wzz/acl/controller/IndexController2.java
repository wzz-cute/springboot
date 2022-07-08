package com.wzz.acl.controller;

import com.alibaba.fastjson.JSONObject;
import com.wzz.acl.service.IndexService;
import com.wzz.util.result.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/acl/index2")
/*@CrossOrigin*/
public class IndexController2 {

    @Autowired
    private IndexService indexService;

    /**
     * 根据token获取用户信息
     */
    @GetMapping("login.html")
    public String info(){
        return "login.html";
    }

}

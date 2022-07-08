package com.wzz.auth.controller;

import com.alibaba.fastjson.JSON;
import com.wzz.auth.entity.User;
import com.wzz.auth.service.IUserService;
import com.wzz.util.JwtUtils;
import com.wzz.util.result.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 【请填写功能名称】Controller
 *
 * @author ruoyi
 * @date 2022-05-28
 */
@RestController
@RequestMapping("/auth/user")
public class UserController {
    @Autowired
    private IUserService UserService;

    @GetMapping("/index")
    public String index() {
        return "<h1>hello auther-server</h1>";
    }

    //注册
    @PostMapping("/register")
    public R registerUser(@RequestBody User user) {
        R r = UserService.registerUser(user);
        return r;
    }

    //查询
    @GetMapping("/getUserInfo")
    public R getUserInfo(HttpServletRequest request){
        //调用jwt工具类的方法，根据Request对象获取头信息，返回用户id
        String userId = JwtUtils.getMemberIdByJwtToken(request);

        //根据数据库根据用户id获取用户信息
        User user = UserService.getById(userId);
        user.setPassword("");
        return new R().put("user",user);
    }

    //登陆
    @PostMapping("/login")
    public R login(@RequestBody User user) {
        R r = UserService.login(user);
        return r;
    }

    //登出

}

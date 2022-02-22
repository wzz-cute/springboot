package com.wzz.redis.controller;


import com.wzz.gulimall.common.utils.R;
import com.wzz.redis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wzzws (wzz6b.com@gmail.com)
 * @version 1.0.0
 * @packageName com.wzz.redis.user
 * @fileName UserController.java
 * @createTime 2022/02/20 20:08:07
 * @lastModify 2022/02/20 20:08:07
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getAllUser")
    public R getAll() {
        return new R().put("data", userService.getAllUser());
    }


}

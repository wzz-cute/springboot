package com.wzz.base.listener.controller;

import com.wzz.base.listener.entity.User;
import com.wzz.base.listener.service.UserService;
import com.wzz.util.result.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 设计一个业务，很多表中冗余了user表信息
 * 一旦user更改后，不好一次性修改user信息
 * 所以我们这使用springboot监听器和监听器模式完成这次案例
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/updateUserInfoBySpringbootListener")
    public R updateUserInfoBySpringbootListener(@RequestBody User user){
        return userService.updateUserInfoBySpringbootListener(user);
    }

    @PostMapping("/updateUserInfoByMyListener")
    public R updateUserInfoByMyListener(@RequestBody User user){
        return userService.updateUserInfoByMyListener(user);
    }

}

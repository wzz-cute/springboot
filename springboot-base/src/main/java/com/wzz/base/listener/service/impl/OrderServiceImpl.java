package com.wzz.base.listener.service.impl;

import com.wzz.base.listener.entity.User;
import com.wzz.base.listener.event.MyListener;
import com.wzz.base.listener.service.OrderService;
import com.wzz.util.result.R;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService, MyListener<User> {
    @Override
    public R modifyUserInfo(User user) {
        System.out.println("Order 修改用户信息");
        return null;
    }

    @Override
    public void execute(User value) {
        modifyUserInfo(value);
    }
}

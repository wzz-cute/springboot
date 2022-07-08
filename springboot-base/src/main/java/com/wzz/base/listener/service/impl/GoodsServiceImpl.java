package com.wzz.base.listener.service.impl;

import com.wzz.base.listener.entity.User;
import com.wzz.base.listener.event.MyListener;
import com.wzz.base.listener.service.GoodsService;
import com.wzz.util.result.R;
import org.springframework.stereotype.Service;

@Service
public class GoodsServiceImpl implements GoodsService, MyListener<User, R> {
    @Override
    public R modifyUserInfo(User user) {
        System.out.println("GOODS 修改用户信息");
        return null;
    }

    @Override
    public void execute(User value) {
        modifyUserInfo(value);
    }
}

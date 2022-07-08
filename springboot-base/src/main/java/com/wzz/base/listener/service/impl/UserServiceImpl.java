package com.wzz.base.listener.service.impl;

import com.wzz.base.listener.entity.User;
import com.wzz.base.listener.event.MyEvent;
import com.wzz.base.listener.event.MyListener;
import com.wzz.base.listener.service.UserService;
import com.wzz.util.result.R;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private ApplicationContext context;

    @Override
    public R updateUserInfoBySpringbootListener(User user) {
        publisher.publishEvent(new MyEvent(user));
        return R.ok().setData("SpringbootListener修改完成");
    }

    @Override
    public R updateUserInfoByMyListener(User user) {
        //获取上下文所有类型
        Map<String, MyListener> stringMyListenerMap = BeanFactoryUtils.beansOfTypeIncludingAncestors(context, MyListener.class);
        for (String s : stringMyListenerMap.keySet()) {
            stringMyListenerMap.get(s).execute(user);
        }
        return null;
    }
}

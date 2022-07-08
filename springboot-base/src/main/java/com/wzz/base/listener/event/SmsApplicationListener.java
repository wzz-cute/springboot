package com.wzz.base.listener.event;

import com.wzz.base.listener.entity.User;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class SmsApplicationListener implements ApplicationListener<MyEvent> {
    @Override
    public void onApplicationEvent(MyEvent event) {
        User user = (User) event.getSource();
        System.out.println("执行更新操作");
    }
}

@Component
class SmsApplicationListener2 implements ApplicationListener<MyEvent> {
    @Override
    public void onApplicationEvent(MyEvent event) {
        User user = (User) event.getSource();
        System.out.println("执行更新操作2");
    }
}

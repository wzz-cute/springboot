package com.wzz.base.listener.event;

import com.wzz.base.listener.entity.User;
import lombok.Data;
import org.springframework.context.ApplicationEvent;

public class MyEvent extends ApplicationEvent {

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public MyEvent(Object source) {
        super(source);
    }
}

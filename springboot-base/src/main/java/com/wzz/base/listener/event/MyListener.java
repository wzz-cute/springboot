package com.wzz.base.listener.event;

public interface MyListener<T,A> {
    void execute(T value);
}

package com.wzz.juc.lock.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@RestController
@RequestMapping("/lock")
public class LockController {
    //重入锁
    /**
     * 可以尝试获锁，tryLock为等待锁，等待时间到了还没有获取到返回
     */
    Lock reentrantLock = new ReentrantLock(true);

    @GetMapping("/ReentrantLock")
    public String reentrantLock() {
//        reentrantLock.lock();//上锁
        try {
//            if (reentrantLock.tryLock(10, TimeUnit.MINUTES)) {//尝试获锁
            if (reentrantLock.tryLock()) {//尝试获锁
                System.out.println("执行业务");
                Thread.sleep(20000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
        return Thread.currentThread().getName();
    }

}

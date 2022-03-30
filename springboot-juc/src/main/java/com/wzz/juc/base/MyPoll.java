package com.wzz.juc.base;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class MyRunablePool implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("MyRunnable线程正在执行：" + Thread.currentThread().getName() + "   " + new Date().getTime());
        }
    }
}

public class MyPoll {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(new MyRunablePool());

        for (int i = 0; i < 10; i++) {
            System.out.println("main主线程正在执行：" + Thread.currentThread().getName() + "   " + new Date().getTime());
        }

    }
}

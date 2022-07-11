package com.wzz.juc.pool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPool {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1);
//        for (int i = 0; i < 10; i++) {
        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            public void run() {
                System.out.println("delay 3 seconds");
            }
        }, 1, 3, TimeUnit.SECONDS);
//        }

    }
}

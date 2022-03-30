package com.wzz.juc.base;

/**
 * 基础Thread 实现run方法
 * run 和 start的区别
 * run执行时 为就绪状态 不会执行
 * start依赖Thread，为执行状态
 */
public class MyThread extends Thread {
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "  "
                    + Thread.currentThread().getId() + " 执行...");
        }
    }
}

class MainThread {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "  "
                    + Thread.currentThread().getId() + " 执行...");
        }
    }
}

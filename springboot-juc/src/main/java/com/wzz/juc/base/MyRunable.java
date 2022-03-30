package com.wzz.juc.base;

public class MyRunable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "  "
                    + Thread.currentThread().getId() + " 执行...");
        }
    }
}

class MainRunable {
    public static void main(String[] args) {
        new Thread(new MyRunable()).start();

        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "  "
                    + Thread.currentThread().getId() + " 执行...");
        }
    }
}

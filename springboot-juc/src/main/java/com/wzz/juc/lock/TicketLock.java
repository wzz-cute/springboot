package com.wzz.juc.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class SaleTicket implements Runnable {
    int num = 100;
    Lock lock = new ReentrantLock(false);

    @Override
    public void run() {
//        while (true) {
//            synchronized (Object.class) {
//                if (num > 0) {
//                    num--;
//                    System.out.println("卖出一张票，还剩" + num + "  "
//                            + Thread.currentThread().getName() + " 执行...");
//                }
//            }
//        }
        sale();
        return;
    }

    private void sale() {
        while (true) {
            lock.lock();
            try {
                if (num > 0) {
                    num--;
                    System.out.println("卖出一张票，还剩" + num + "  "
                            + Thread.currentThread().getName() + " 执行...");
                }
            } catch (Exception e) {

            } finally {
                lock.unlock();
            }
        }
    }
}

class Ticket {
    public static void main(String[] args) {
        SaleTicket saleTicket = new SaleTicket();
        Thread aa = new Thread(saleTicket, "aa");
        Thread bb = new Thread(saleTicket, "bb");
        Thread cc = new Thread(saleTicket, "cc");

        bb.start();
        aa.start();
        cc.start();
    }
}

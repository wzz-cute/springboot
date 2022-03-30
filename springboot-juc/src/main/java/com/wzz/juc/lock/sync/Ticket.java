package com.wzz.juc.lock.sync;

class SaleTicket implements Runnable {
    int num = 100;

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

    private synchronized void sale() {
        while (true) {
            if (num > 0) {
                num--;
                System.out.println("卖出一张票，还剩" + num + "  "
                        + Thread.currentThread().getName() + " 执行...");
            }
        }
    }
}

public class Ticket {
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

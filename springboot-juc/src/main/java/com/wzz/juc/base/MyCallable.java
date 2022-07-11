package com.wzz.juc.base;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class MyCallable implements Callable<Object> {
    @Override
    public Object call() throws Exception {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "  "
                    + Thread.currentThread().getId() + " 执行...");
        }
        return "Callable 执行完毕......";
    }
}

class MainCallable {
    public static void main(String[] args) {
        FutureTask task = new FutureTask(new MyCallable());
        Thread thread = new Thread(task);

        thread.start();

        //判断是否完成任务
        System.out.println(task.isDone());

//        System.out.println(task.cancel(true));


        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "  "
                    + Thread.currentThread().getId() + " 执行...");
        }

        try {
            //获取任务结果
            System.out.println(task.get());
            System.out.println(task.isDone());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

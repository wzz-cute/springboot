package com.wzz.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BIOServer {
    public static void main(String[] args) throws IOException {
        //线程池机制
        //思路
        //1.创建一个线程池
        //2.有客户连接就创建一个线程与之通信(单独写一个方法)
        ExecutorService executorService = Executors.newCachedThreadPool();

        //创建ServerSocket
        ServerSocket serverSocket = new ServerSocket(6666);

        System.out.println("服务启动");

        while (true) {
            System.out.println("线程信息:id=" + Thread.currentThread().getId() + "  名字=" + Thread.currentThread().getName());
            //监听，等待客户端连接
            System.out.println("等待连接");
            final Socket socket = serverSocket.accept();
            System.out.println("连接到一个客户端");

            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    //与客户端通信
                    hander(socket);
                }
            });
        }
    }

    public static void hander(Socket socket) {

        try {
            System.out.println("线程信息:id=" + Thread.currentThread().getId() + "  名字=" + Thread.currentThread().getName());
            byte[] bytes = new byte[1024];
            //通过socket获取输入流
            InputStream inputStream = socket.getInputStream();

            while (true) {
                System.out.println("线程信息:id=" + Thread.currentThread().getId() + "  名字=" + Thread.currentThread().getName());
                System.out.println("read...");

                int read = inputStream.read(bytes);

                if (read != -1) {
                    System.out.println(new String(bytes, 0, read));
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("关闭和client连接");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}

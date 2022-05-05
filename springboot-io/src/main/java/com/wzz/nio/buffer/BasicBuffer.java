package com.wzz.nio.buffer;

import java.nio.IntBuffer;

public class BasicBuffer {
    public static void main(String[] args) {
        IntBuffer intBuffer  = IntBuffer.allocate(5);
        intBuffer.put(10);
        intBuffer.put(20);
        intBuffer.put(30);
        intBuffer.put(40);
        intBuffer.put(50);

        //读写切换  将写数据切换到读数据
        intBuffer.flip();
        System.out.println(intBuffer.capacity());

        while (intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }

    }


}

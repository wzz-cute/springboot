package com.wzz.nio.buffer;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOFileChannel01 {
    public static void main(String[] args) throws Exception {
        String str = "hrh is dsb";

        //创建一个输出流==》channel
        FileOutputStream fileOutputStream = new FileOutputStream("d:\\file01.txt");

        //通过fileOutputStream 获取通道channel
        //fileChannel真是类型是FileChannelImpl
        FileChannel fileChannel = fileOutputStream.getChannel();

        //创建缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put(str.getBytes());

        //读写转换
        byteBuffer.flip();

        //将byBuffer写入fileChannel
        fileChannel.write(byteBuffer);
        fileOutputStream.close();
        fileChannel.close();
    }
}

package com.wzz.nio.buffer;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOFileChannel02 {
    public static void main(String[] args) throws Exception {
        String str = "hrh is dsb";

        //创建一个输出流==》channel
        File file = new File("d:\\file01.txt");
        FileInputStream fileInputStream = new FileInputStream(file);

        //fileInputStream 获取通道channel
        //fileChannel真是类型是FileChannelImpl
        FileChannel fileChannel = fileInputStream.getChannel();

        //创建缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());
        byteBuffer.put(str.getBytes());

        //将byBuffer写入fileChannel  不写好像一样的
//        fileChannel.read(byteBuffer);

        System.out.println(new String(byteBuffer.array()));
        fileInputStream.close();
        fileChannel.close();
    }
}

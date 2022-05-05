package com.wzz.io.reptile;

import java.io.*;
import java.util.Scanner;

public class CharacterStream {
    public static void main(String[] args) throws Exception {
        bufferedWrite();
        System.out.println("填写结果:");
        bufferedReader();
    }

    /**
     * 写文件
     */
    private static void bufferedWrite() throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\character\\test.txt",true);
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
        Scanner in = new Scanner(System.in);
        StringBuilder sb = new StringBuilder("");
        while (true) {
            String s = in.nextLine();
            if (s.equals("exit")) {
                break;
            }
            sb.append(s);
            bufferedWriter.write(s);
//            bufferedWriter.append(sb);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }
        bufferedWriter.close();
    }

    /**
     * 读文件
     *
     * @throws Exception
     */
    private static void bufferedReader() throws Exception {
        BufferedReader bufferedReader = new
                BufferedReader(new InputStreamReader(new FileInputStream("D:\\character\\test.txt")));
        String context;
        while ((context = bufferedReader.readLine()) != null) {
            System.out.println(context);
        }

        bufferedReader.close();
    }
}

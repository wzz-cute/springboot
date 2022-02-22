package com.wzz.io.file;

import java.io.File;
import java.util.Arrays;
import java.io.FileInputStream;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class FileTest {
    public static void main(String[] args) throws Exception {
//        fileConstruction();
//        createAndDelFile();
//        model();
//        model1();
//        model2();
//        model3();
        music();
    }

    /**
     * 构造方法 创建file对象
     * File(String pathname) 通过将给定路径名字符串转换为抽象路径名来创建一个新 File 实例。
     * <p>
     * File(String parent,String child) 根据指定的父路径和文件路径创建一个新File对象实例
     * <p>
     * File(File parent,String child) 根据指定的父路径对象和文件路径创建一个新的File对象实例
     */
    private static void fileConstruction() {
        File file = new File("E:\\IntallPath" +
                "\\ProgramingLanguage\\Code\\JAVA\\Dream\\springboot" +
                "\\springboot-io\\src\\main\\resources\\file\\txt1.txt");
        File file2 = new File(new File("E:\\IntallPath" +
                "\\ProgramingLanguage\\Code\\JAVA\\Dream\\springboot" +
                "\\springboot-io\\src\\main\\resources\\file\\"), "txt2.txt");
    }

    /**
     * boolean createNewFile();指定路径不存在该文件时创建文件，返回true 否则false
     * <p>
     * boolean mkdir（） 当指定的单击文件夹不存在时创建文件夹并返回true 否则false
     * <p>
     * boolean mkdirs（） 但指定的多级文件夹在某一级文件夹不存在时，创建多级文件夹并返回true 否则false
     * <p>
     * boolean delete（） 删除文件或者删除单级文件夹
     *
     * @throws Exception
     */
    private static void createAndDelFile() throws Exception {
//        File file1 = new File(new File("E:\\"), "txt2.txt");
//        System.out.println(file1.createNewFile());

        File file = new File("E:\\c");
        File file2 = new File("c");//在项目跟文件夹创建
        File file1 = new File("E:\\c\\b\\txt1.txt");
        System.out.println(file2.mkdir());
        System.out.println(file.mkdir());
        System.out.println(file1.mkdirs());
        System.out.println(file2.delete());
    }

    /**
     * boolean isAbsolute() 判断当前路径是否是绝对路径
     * <p>
     * boolean isDirectory() 判断当前的目录是否存在
     * <p>
     * boolean isFile() 判断当前的目录是否是一个文件
     * <p>
     * boolean isHidden() 判断当前路径是否是一隐藏文件
     */
    public static void model() {
        File file1 = new File("E:\\c\\b\\txt1.txt");
        System.out.println(file1.isAbsolute());
        System.out.println(file1.isFile());

        //.isDirectory()
        System.out.println(new File("E:\\c\\b").isDirectory());
        System.out.println(new File("E:\\c\\b").isHidden());
        System.out.println(new File("E:\\c\\b").isFile());
    }

    /**
     * File getAbsoluteFile() 获取文件的绝对路径，返回File对象
     * <p>
     * String getAbsolutePath() 获取文件的绝对路径，返回路径的字符串
     * <p>
     * String getParent() 获取当前路径的父级路径，以字符串形式返回该父级路径
     * <p>
     * String getName() 获取文件或文件夹的名称
     * <p>
     * String getPath() 获取File对象中封装的路径
     */
    public static void model1() {
        File file = new File("E:\\IntallPath\\ProgramingLanguage\\Code\\JAVA\\Dream" +
                "\\springboot\\springboot-io\\src\\main\\java\\com\\wzz\\io\\file\\FileTest.java");
        System.out.println(file.getAbsoluteFile());
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getParent());
        System.out.println(file.getParentFile());
        System.out.println(file.getName());
        System.out.println(file.getPath());
    }

    /**
     * long lastModified() 以毫秒值返回最后修改时间
     * <p>
     * long length() 返回文件的字节数
     * <p>
     * boolean renameTo(File dest) 将当前File对象所指向的路径修改为指定File所指向的路径
     */
    public static void model2() throws Exception {
        File file1 = new File("E:\\c\\b\\txt1.txt\\111.txt");
        System.out.println(file1.lastModified());
        System.out.println(file1.length());
        System.out.println(file1.renameTo(new File("E:\\c\\b\\txt1.txt\\222.txt")));
    }

    /**
     * String[] list(); 以字符串的形式返回当前路径下所有的文件和文件夹的名称
     * <p>
     * File[] listFile 以File对象的形式返回当前路径下的所有文件和文件夹名称
     * <p>
     * Static File[] listRoots() 获取计算机中的所有盘符
     */
    public static void model3() {
        File file1 = new File("E:\\IntallPath\\ProgramingLanguage\\Code\\JAVA\\Dream\\springboot" +
                "\\springboot-io\\src\\main\\java\\com\\wzz\\io\\file\\music");
        System.out.println(Arrays.toString(file1.list()));
        System.out.println(Arrays.toString(file1.listFiles()));
        System.out.println(Arrays.toString(file1.listRoots()));
        System.out.println(file1.toURI());

    }

    /**
     * 播放音乐
     */
    public static void music() throws Exception {
        File file1 = new File("E:\\IntallPath\\ProgramingLanguage\\Code\\JAVA\\Dream\\springboot" +
                "\\springboot-io\\src\\main\\java\\com\\wzz\\io\\file\\music");
        for (File file : file1.listFiles()) {
            System.out.println(file.getPath());
            FileInputStream fileau = new FileInputStream(file.getPath());
            AudioStream as = new AudioStream(fileau);
            AudioPlayer.player.start(as);
        }

    }
}

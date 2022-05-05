package com.wzz.offer.pdf;

import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import com.spire.doc.documents.XHTMLValidationType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 根据html模板文件转出pdf
 */
@RestController
public class HtmlToPdf {

    @GetMapping("getPdfHtml")
    public String toPdf(HttpServletRequest request, HttpServletResponse response) {
//        response.setContentType("application/force-download");// 设置强制下载不打开
//        response.addHeader("Content-Disposition", "attachment;fileName=pdfInfo.pdf");// 设置文件名
        List<Student> stus = test();

        BufferedWriter writer = null;

        StringBuilder sb = null;
        BufferedInputStream inputStream = null;
        OutputStream os = null;
        try {
            File file = new File("a.html");
            boolean newFile = file.createNewFile();
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));

            sb = new StringBuilder("<table border=\"1\">");

            //添加表头
            sb.append("<tr>");
            sb.append("<td>学生id</td>");
            sb.append("<td>学生姓名</td>");
            sb.append("<td>学生昵称</td>");
            sb.append("</tr>");

            //添加主体
            for (Student s : stus) {
                sb.append("<tr>");
                sb.append(String.format("<td>%d</td>", s.getId()));
                sb.append(String.format("<td>%s</td>", s.getName()));
                sb.append(String.format("<td>%s</td>", s.getNickName()));
                sb.append("</tr>");
            }
//            while (true) {
//                if (s.equals("exit")) {
//                    break;
//                }
//                sb.append(s);
//                bufferedWriter.write(s);
//            bufferedWriter.append(sb);
//                bufferedWriter.newLine();
//                bufferedWriter.flush();
//            }
            sb.append("</table>");
            writer.write(sb.toString());
            writer.flush();

            //加载HTML文档
            Document document = new Document();
            document.loadFromFile("a.html", FileFormat.Html, XHTMLValidationType.None);

            //文档另存为PDF
            document.saveToFile("Result.pdf",FileFormat.PDF);

            try {
                inputStream = new BufferedInputStream(new FileInputStream("Result.pdf"));
                os = response.getOutputStream();
                int i;
                while ((i = inputStream.read()) != -1) {
                    os.write(i);
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
                if (os != null) {
                    os.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            //用完记得删除
            File file = new File("a.html");
            if (file.delete()) {
                System.out.println("html删除成功");
            }
            File file1 = new File("Result.pdf");
            if (file1.delete()) {
                System.out.println("pdf删除成功");
            }
            File file2 = new File("2.pdf");
            if (file2.delete()) {
                System.out.println("pdf2删除成功");
            }
        }
        return sb.toString();
    }

    public List<Student> test() {
        List<Student> stus = new ArrayList<>();
        stus.add(new Student(1, "wzz", "好人"));
        stus.add(new Student(2, "hrh", "邪恶的人"));
        stus.add(new Student(3, "li ju", "奖励哥"));
        stus.add(new Student(4, "lpf", "帮奖励哥奖励的人"));
        return stus;
    }
}

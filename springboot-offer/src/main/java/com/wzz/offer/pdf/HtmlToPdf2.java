//package com.wzz.offer.pdf;
//
//import com.itextpdf.text.pdf.BaseFont;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.xhtmlrenderer.pdf.ITextFontResolver;
//import org.xhtmlrenderer.pdf.ITextRenderer;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.*;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * 根据html模板文件转出pdf
// */
//@RestController
//public class HtmlToPdf2 {
//
//    @GetMapping("getPdfHtml2")
//    public String toPdf(HttpServletRequest request, HttpServletResponse response) {
//        response.setContentType("application/force-download");// 设置强制下载不打开
//        response.addHeader("Content-Disposition", "attachment;fileName=pdfInfo.pdf");// 设置文件名
//        List<Student> stus = test();
//
//        StringBuilder sb = null;
//        try {
//
//            sb = new StringBuilder("<table border=\"1\">");
//
//            //添加表头
//            sb.append("<tr>");
//            sb.append("<td>学生id</td>");
//            sb.append("<td>学生姓名</td>");
//            sb.append("<td>学生昵称</td>");
//            sb.append("</tr>");
//
//            //添加主体
//            for (Student s : stus) {
//                sb.append("<tr>");
//                sb.append(String.format("<td>%d</td>", s.getId()));
//                sb.append(String.format("<td>%s</td>", s.getName()));
//                sb.append(String.format("<td>%s</td>", s.getNickName()));
//                sb.append("</tr>");
//            }
////            while (true) {
////                if (s.equals("exit")) {
////                    break;
////                }
////                sb.append(s);
////                bufferedWriter.write(s);
////            bufferedWriter.append(sb);
////                bufferedWriter.newLine();
////                bufferedWriter.flush();
////            }
//            sb.append("</table>");
//
////            OutputStream os = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\in.pdf");    //生成PDF文件的路径
//            OutputStream os = response.getOutputStream();    //生成PDF文件的路径
//            ITextRenderer renderer = new ITextRenderer();
//            ITextFontResolver font = renderer.getFontResolver();
//            font.addFont("C:\\Windows\\Fonts\\STXINWEI.TTF",BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);//添加中文识别，这里是设置的宋体，Linux下要换成对应的字体
////            font.addFont("C:/WINDOWS/Fonts/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);//添加中文识别，这里是设置的宋体，Linux下要换成对应的字体
//            renderer.setDocumentFromString(sb.toString());
//
//            renderer.layout();
//            renderer.createPDF(os);
//            renderer.finishPDF();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//        }
//        return sb.toString();
//    }
//
//    public List<Student> test() {
//        List<Student> stus = new ArrayList<>();
//        stus.add(new Student(1, "wzz", "好人"));
//        stus.add(new Student(2, "hrh", "邪恶的人"));
//        stus.add(new Student(3, "li ju", "奖励哥"));
//        stus.add(new Student(4, "lpf", "帮奖励哥奖励的人"));
//        return stus;
//    }
//}

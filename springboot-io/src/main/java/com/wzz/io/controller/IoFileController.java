package com.wzz.io.controller;

import com.wzz.gulimall.common.utils.R;
import org.springframework.http.HttpHeaders;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
@RequestMapping("/file")
public class IoFileController {

    @GetMapping("getPath")
    public void getPath() {
//        String path = this.getClass().getResource("/static/pic/鬼刀0c6c9efc-8b0b-433f-8c6c-385a1f49ac1b.jpg").getPath();
//        String substring = path.substring(1, path.length() - 1);
//        System.out.println(this.getClass().getResource("").getPath());
        File file = null;
        try {
            String path = ResourceUtils.getURL("classpath:").getPath() + "鬼刀0c6c9efc-8b0b-433f-8c6c-385a1f49ac1b.jpg";
            file = new File(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (file.exists()) {
            System.out.println(file.delete());
        }
    }

    @PostMapping("/uploadFileSingle")
    public R upFileSingle(@RequestParam("file") MultipartFile file) {//这是springboot专门搞的文件上传功能
        if (file.isEmpty()) {
            return R.error();
        }
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            String path = "D:\\";//你要保存的路径
            File file1 = new File(path);
            if (!file1.exists()) {
                file1.mkdirs();//如果路径不存在就创建
            }
            //获取上传时的文件名
            String fileName = file.getOriginalFilename();
            //注意是路径+文件名
            File targetFile = new File(path + fileName);

            //获取文件的输出流
            inputStream = file.getInputStream();
            outputStream = new FileOutputStream(targetFile);
            //最后使用资源访问器FileCopyUtils的copy方法拷贝文件
//            方法1： 直接复制比较快
//            FileCopyUtils.copy(inputStream, outputStream);

//            方法2:
            int len;
            while ((len = inputStream.read()) != -1) {
                outputStream.write(len);
                outputStream.flush();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return R.ok();
    }

    @PostMapping("/uploadFileMany")
    public R uploadFileMany(HttpServletRequest request) {

        List<MultipartFile> list_files = ((MultipartHttpServletRequest) request).getFiles("files");

        if (list_files.isEmpty()) {
            return R.error();
        }

        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            for (MultipartFile file : list_files) {
                String path = "D:\\uploadFileMany\\";
                File file1 = new File(path);
                if (!file1.exists()) {
                    file1.mkdirs();
                }
                //获取上传时的文件名
                String fileName = file.getOriginalFilename();
                //注意是路径+文件名
                File targetFile = new File(path + fileName);

                //获取文件的输出流
                inputStream = file.getInputStream();
                outputStream = new FileOutputStream(targetFile);
                //最后使用资源访问器FileCopyUtils的copy方法拷贝文件
//            方法1：
                FileCopyUtils.copy(inputStream, outputStream);

//            方法2:
//                int len;
//                while ((len = inputStream.read()) != -1) {
//                    outputStream.write(len);
//                    outputStream.flush();
//                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return R.ok();
    }

    @GetMapping("/download")
    public String downloadFile(HttpServletRequest request, HttpServletResponse response) {
        String fileName = "鬼刀0a2b9e00-e599-4f4a-883c-9615075485db.jpg";// 文件名
        String substring = fileName.substring(fileName.lastIndexOf("."), fileName.length());
        if (fileName != null) {
            //设置文件路径
            File file = new File("D:\\pic\\鬼刀\\" + fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" + UUID.randomUUID().toString() + substring);// 设置文件名
                BufferedInputStream bis = null;
                try {
                    bis = new BufferedInputStream(new FileInputStream(file));
                    OutputStream os = response.getOutputStream();
                    int i;
                    while ((i = bis.read()) != -1) {
                        os.write(i);
                    }
                    return "下载成功";
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return "下载失败";
    }

    @GetMapping("/zipFile")
    public void zipFile(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=download.zip");

        //这个是文件夹的绝对路径，如果想要相对路径就自行了解写法
        String sourceFile = "D:\\pic\\鬼刀";
        //这个是压缩之后的文件绝对路径
//        FileOutputStream fos = new FileOutputStream(
//                "E:\\2-project\\15-henanhuagong\\CEPOP\\trunk\\zhnh\\uploadFile\\hhnec.zip");
        ZipOutputStream zipOut = new ZipOutputStream(response.getOutputStream());
        File fileToZip = new File(sourceFile);

        zipFile(fileToZip, fileToZip.getName(), zipOut);
        zipOut.close();
//        fos.close();
    }

    private void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
        if (fileToZip.isHidden()) {
            return;
        }
        if (fileToZip.isDirectory()) {
            if (fileName.endsWith("/")) {
                zipOut.putNextEntry(new ZipEntry(fileName));
                zipOut.closeEntry();
            } else {
                zipOut.putNextEntry(new ZipEntry(fileName + "/"));
                zipOut.closeEntry();
            }
            File[] children = fileToZip.listFiles();
            for (File childFile : children) {
                zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
            }
            return;
        }
        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileName);
        zipOut.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }
        fis.close();
    }
}

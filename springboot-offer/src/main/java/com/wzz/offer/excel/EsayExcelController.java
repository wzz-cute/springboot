package com.wzz.offer.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.event.Listener;
import com.wzz.offer.entity.CategoryEntity;
import com.wzz.offer.linter.DemoListener;
import com.wzz.offer.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("esay-excel")
public class EsayExcelController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void downloadExcel(HttpServletResponse response) throws IOException {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            String fileName = format.format(new Date()) + URLEncoder.encode("内鬼数据", "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            List<CategoryEntity> list = categoryService.list();
            EasyExcel.write(response.getOutputStream(), CategoryEntity.class).sheet("模板").doWrite(list);
        } catch (Exception e) {
        }
    }

    @RequestMapping(value = "/daoru", method = RequestMethod.GET)
    public void batchReadFile(@RequestParam(required = false, name = "file") MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        if (!(suffixName.equals(".xlsx"))) {
            System.out.println("请上传xlsx格式文件");
        }
        DemoListener demoListener = new DemoListener(categoryService);
        EasyExcel.read(file.getInputStream(), CategoryEntity.class, demoListener).sheet().doRead();
    }
}

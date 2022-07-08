package com.wzz.es.controller;

import java.util.List;

import com.wzz.es.domain.PmsCategory;
import com.wzz.es.service.IPmsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * 商品三级分类Controller
 *
 * @author ruoyi
 * @date 2022-05-22
 */
@RestController
@RequestMapping("/es/category")
public class PmsCategoryController //extends BaseController
{
    private String prefix = "es/category";

    @Autowired
    private IPmsCategoryService pmsCategoryService;

    //@RequiresPermissions("es:category:view")
    @GetMapping()
    public String category() {
        return prefix + "/category";
    }

    /**
     * 查询商品三级分类列表
     */
    //@RequiresPermissions("es:category:list")
    @PostMapping("/list")
    @ResponseBody
    public List<PmsCategory> list(@RequestBody PmsCategory pmsCategory) {
        List<PmsCategory> list = pmsCategoryService.selectPmsCategoryList(pmsCategory);
        return list;
    }


    /**
     * 新增保存商品三级分类
     */
    //@RequiresPermissions("es:category:add")
    //@Log(title = "商品三级分类", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public int addSave(@RequestBody PmsCategory pmsCategory) {
        return pmsCategoryService.insertPmsCategory(pmsCategory);
    }

    /**
     * 修改商品三级分类
     */
    //@RequiresPermissions("es:category:edit")
    @GetMapping("/edit/{catId}")
    public int edit(@RequestBody PmsCategory pmsCategory) {
        return pmsCategoryService.updatePmsCategory(pmsCategory);
    }

    /**
     * 删除商品三级分类
     */
    //@RequiresPermissions("es:category:remove")
    //@Log(title = "商品三级分类", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public int remove(String ids) {
        return pmsCategoryService.deletePmsCategoryByCatIds(ids);
    }
}

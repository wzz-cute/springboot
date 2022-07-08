package com.wzz.es.controller;

import java.util.List;

import com.wzz.es.domain.PmsBrand;
import com.wzz.es.service.IPmsBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * 品牌Controller
 *
 * @author ruoyi
 * @date 2022-05-22
 */
@RestController
@RequestMapping("/es/brand")
public class PmsBrandController //extends BaseController
{
    private String prefix = "es/brand";

    @Autowired
    private IPmsBrandService pmsBrandService;

    //@RequiresPermissions("es:brand:view")
    @GetMapping()
    public String brand()
    {
        return prefix + "/brand";
    }

    /**
     * 查询品牌列表
     */
    //@RequiresPermissions("es:brand:list")
    @PostMapping("/list")
    @ResponseBody
    public List<PmsBrand> list(@RequestBody PmsBrand pmsBrand)
    {
        List<PmsBrand> list = pmsBrandService.selectPmsBrandList(pmsBrand);
        return list;
    }


    /**
     * 新增保存品牌
     */
    //@RequiresPermissions("es:brand:add")
    //@Log(title = "品牌", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public int addSave(@RequestBody PmsBrand pmsBrand)
    {
        return pmsBrandService.insertPmsBrand(pmsBrand);
    }

    /**
     * 修改品牌
     */
    //@RequiresPermissions("es:brand:edit")
    @GetMapping("/edit/{brandId}")
    public int edit(@RequestBody PmsBrand pmsBrand)
    {
        return pmsBrandService.updatePmsBrand(pmsBrand);
    }

    /**
     * 删除品牌
     */
    //@RequiresPermissions("es:brand:remove")
    //@Log(title = "品牌", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public int remove(String ids)
    {
        return pmsBrandService.deletePmsBrandByBrandIds(ids);
    }
}

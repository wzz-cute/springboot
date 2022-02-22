package com.wzz.redis.controller;

import com.wzz.gulimall.common.utils.PageUtils;
import com.wzz.gulimall.common.utils.R;
import com.wzz.redis.entity.PmsCategoryEntity;
import com.wzz.redis.service.PmsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * 商品三级分类
 *
 * @author wzz
 * @email 3325667808@qq.com
 * @date 2021-12-24 20:27:15
 */
@RestController
@RequestMapping("ware/pmscategory")
public class PmsCategoryController {
    @Autowired
    private PmsCategoryService pmsCategoryService;


    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("ware:pmscategory:list")
    public R list(@RequestParam Map<String, Object> params){
        List<PmsCategoryEntity> page = pmsCategoryService.getInfoByParms(params);

        return R.ok().put("page", page);
    }

    @RequestMapping("/getPmsAndUser")
    public R getPmsAndUser(){
        List<PmsCategoryEntity> pmsCategoryEntities =  pmsCategoryService.getPmsAndUser();
        return new R().put("data",pmsCategoryEntities);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{catId}")
    //@RequiresPermissions("ware:pmscategory:info")
    public R info(@PathVariable("catId") Long catId){
        System.out.println(catId);
		PmsCategoryEntity pmsCategory = pmsCategoryService.getById(catId);

        return R.ok().put("pmsCategory", pmsCategory);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("ware:pmscategory:save")
    public R save(@RequestBody PmsCategoryEntity pmsCategory){
		pmsCategoryService.save(pmsCategory);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("ware:pmscategory:update")
    public R update(@RequestBody PmsCategoryEntity pmsCategory){
		pmsCategoryService.updateById(pmsCategory);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("ware:pmscategory:delete")
    public R delete(@RequestBody Long[] catIds){
		pmsCategoryService.removeByIds(Arrays.asList(catIds));

        return R.ok();
    }

}

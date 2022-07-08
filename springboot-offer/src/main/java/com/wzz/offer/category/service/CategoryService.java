package com.wzz.offer.category.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wzz.util.PageUtils;
import com.wzz.offer.category.entity.CategoryEntity;

import java.util.Map;

/**
 * 商品三级分类
 *
 * @author wzz
 * @email 3325667808@qq.com
 * @date 2022-02-11 14:40:21
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


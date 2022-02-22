package com.wzz.offer.category.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wzz.offer.category.entity.CategoryEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author wzz
 * @email 3325667808@qq.com
 * @date 2022-02-11 14:40:21
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}

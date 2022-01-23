package com.wzz.redis.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wzz.redis.entity.PmsCategoryEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 商品三级分类
 * 
 * @author wzz
 * @email 3325667808@qq.com
 * @date 2021-12-24 20:27:15
 */
@Mapper
public interface PmsCategoryDao extends BaseMapper<PmsCategoryEntity> {

    List<PmsCategoryEntity> getInfoByParms(@Param("params") Map<String, Object> params);
}

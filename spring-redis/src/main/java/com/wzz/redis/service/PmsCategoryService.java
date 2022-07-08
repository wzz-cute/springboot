package com.wzz.redis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wzz.util.PageUtils;
import com.wzz.redis.entity.PmsCategoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品三级分类
 *
 * @author wzz
 * @email 3325667808@qq.com
 * @date 2021-12-24 20:27:15
 */
public interface PmsCategoryService extends IService<PmsCategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<PmsCategoryEntity> getInfoByParms(Map<String, Object> params);

    List<PmsCategoryEntity> getData();

    List<PmsCategoryEntity> getPmsAndUser();
}


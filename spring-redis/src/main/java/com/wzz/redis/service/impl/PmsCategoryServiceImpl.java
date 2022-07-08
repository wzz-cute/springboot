package com.wzz.redis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wzz.util.PageUtils;
import com.wzz.util.Query;
import com.wzz.redis.dao.PmsCategoryDao;
import com.wzz.redis.entity.PmsCategoryEntity;
import com.wzz.redis.service.PmsCategoryService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("pmsCategoryService")
@CacheConfig(cacheNames = "pmsData")
public class PmsCategoryServiceImpl extends ServiceImpl<PmsCategoryDao, PmsCategoryEntity> implements PmsCategoryService {

    @Autowired
    private PmsCategoryDao pmsCategoryDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PmsCategoryEntity> page = this.page(
                new Query<PmsCategoryEntity>().getPage(params),
                new QueryWrapper<PmsCategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<PmsCategoryEntity> getInfoByParms(Map<String, Object> params) {
        Integer page = Integer.parseInt(params.get("page").toString()) ;
        Integer size = Integer.parseInt(params.get("pageSize").toString()) ;
        Integer i = (page - 1) * size;
        params.put("page",i);
        params.put("pageSize",size);
        return pmsCategoryDao.getInfoByParms(params);
    }

    //    @Cacheable(value = "my-redis-cache1",key = "'book'+#bid",condition = "#bid>10")
//    @Cacheable(cacheNames="content",key = "data")
    @Cacheable(value = {"category"},key = "#root.method.name")
    public List<PmsCategoryEntity> getData() {
        System.out.println("数据库提取");
        Map<String, Object> parse = new HashMap<>();
        parse.put("page", 1);
        parse.put("pageSize", 10);

        return pmsCategoryDao.getInfoByParms(parse);
    }

    @Override
    public List<PmsCategoryEntity> getPmsAndUser() {
        return pmsCategoryDao.getPmsAndUser();
    }

}
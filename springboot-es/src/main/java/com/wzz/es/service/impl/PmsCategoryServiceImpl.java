package com.wzz.es.service.impl;

import java.util.List;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wzz.es.domain.PmsCategory;
import com.wzz.es.mapper.PmsCategoryMapper;
import com.wzz.es.service.IPmsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * 商品三级分类Service业务层处理
 *
 * @author ruoyi
 * @date 2022-05-22
 */
@Service
public class PmsCategoryServiceImpl extends ServiceImpl<PmsCategoryMapper, PmsCategory> implements IPmsCategoryService {
    @Autowired
    private PmsCategoryMapper pmsCategoryMapper;

    /**
     * 查询商品三级分类
     *
     * @param catId 商品三级分类主键
     * @return 商品三级分类
     */
    @Override
    public PmsCategory selectPmsCategoryByCatId(Long catId) {
        return pmsCategoryMapper.selectPmsCategoryByCatId(catId);
    }

    /**
     * 查询商品三级分类列表
     *
     * @param pmsCategory 商品三级分类
     * @return 商品三级分类
     */
    @Override
    public List<PmsCategory> selectPmsCategoryList(PmsCategory pmsCategory) {
        return pmsCategoryMapper.selectPmsCategoryList(pmsCategory);
    }

    /**
     * 新增商品三级分类
     *
     * @param pmsCategory 商品三级分类
     * @return 结果
     */
    @Override
    public int insertPmsCategory(PmsCategory pmsCategory) {
        return pmsCategoryMapper.insertPmsCategory(pmsCategory);
    }

    /**
     * 修改商品三级分类
     *
     * @param pmsCategory 商品三级分类
     * @return 结果
     */
    @Override
    public int updatePmsCategory(PmsCategory pmsCategory) {
        return pmsCategoryMapper.updatePmsCategory(pmsCategory);
    }

    /**
     * 批量删除商品三级分类
     *
     * @param catIds 需要删除的商品三级分类主键
     * @return 结果
     */
    @Override
    public int deletePmsCategoryByCatIds(String catIds) {
        return pmsCategoryMapper.deletePmsCategoryByCatIds(Convert.toStrArray(catIds));
    }

    /**
     * 删除商品三级分类信息
     *
     * @param catId 商品三级分类主键
     * @return 结果
     */
    @Override
    public int deletePmsCategoryByCatId(Long catId) {
        return pmsCategoryMapper.deletePmsCategoryByCatId(catId);
    }

    @Override
    public Integer addPmsCategory(PmsCategory pmsCategory) {
        return this.save(pmsCategory) ? 1 : 0;
    }

    @Override
    public Integer delPmsCategory(Integer catId) {
        return this.removeById(catId) ? 1 : 0;
    }

    @Override
    public Integer modifyPmsCategory(PmsCategory pmsCategory) {
        return this.updateById(pmsCategory) ? 1 : 0;
    }

    @Override
    public PmsCategory getPmsCategoryById(Integer catId) {
        QueryWrapper<PmsCategory> wrapper = new QueryWrapper<>();
        wrapper.eq("cat_id", catId);
        return this.getOne(wrapper);
    }

    @Override
    public IPage<PmsCategory> getPmsCategory(PmsCategory pmsCategory) {
        IPage<PmsCategory> page = new Page<>(2, 5);
        QueryWrapper<PmsCategory> wrapper = new QueryWrapper<>();
        if (!ObjectUtils.isEmpty(pmsCategory.getCatId())) wrapper.eq("cat_id", pmsCategory.getCatId());

        IPage<PmsCategory> categoryIPage = this.page(page, wrapper);
        return categoryIPage;
    }

    @Override
    public Integer addBatchPmsCategory(List<PmsCategory> pmsCategory) {
        return this.saveBatch(pmsCategory) ? 1 : 0;
    }

    @Override
    public Integer delBatchPmsCategory(List<Integer> catId) {
        return this.removeByIds(catId) ? 1 : 0;
    }

    @Override
    public Integer modifyBatchPmsCategory(List<PmsCategory> pmsCategory) {
        return this.updateBatchById(pmsCategory) ? 1 : 0;
    }
}

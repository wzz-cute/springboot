package com.wzz.es.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wzz.es.domain.PmsCategory;

import java.util.List;

/**
 * 商品三级分类Service接口
 * 
 * @author ruoyi
 * @date 2022-05-22
 */
public interface IPmsCategoryService extends IService<PmsCategory>
{
    /**
     * 查询商品三级分类
     * 
     * @param catId 商品三级分类主键
     * @return 商品三级分类
     */
    public PmsCategory selectPmsCategoryByCatId(Long catId);

    /**
     * 查询商品三级分类列表
     * 
     * @param pmsCategory 商品三级分类
     * @return 商品三级分类集合
     */
    public List<PmsCategory> selectPmsCategoryList(PmsCategory pmsCategory);

    /**
     * 新增商品三级分类
     * 
     * @param pmsCategory 商品三级分类
     * @return 结果
     */
    public int insertPmsCategory(PmsCategory pmsCategory);

    /**
     * 修改商品三级分类
     * 
     * @param pmsCategory 商品三级分类
     * @return 结果
     */
    public int updatePmsCategory(PmsCategory pmsCategory);

    /**
     * 批量删除商品三级分类
     * 
     * @param catIds 需要删除的商品三级分类主键集合
     * @return 结果
     */
    public int deletePmsCategoryByCatIds(String catIds);

    /**
     * 删除商品三级分类信息
     * 
     * @param catId 商品三级分类主键
     * @return 结果
     */
    public int deletePmsCategoryByCatId(Long catId);


    //mybatis-plus
    Integer addPmsCategory(PmsCategory pmsCategory);

    Integer delPmsCategory(Integer catId);

    Integer modifyPmsCategory(PmsCategory pmsCategory);

    PmsCategory getPmsCategoryById(Integer catId);

    IPage<PmsCategory> getPmsCategory(PmsCategory pmsCategory);



    Integer addBatchPmsCategory(List<PmsCategory> pmsCategory);

    Integer delBatchPmsCategory(List<Integer> catId);

    Integer modifyBatchPmsCategory(List<PmsCategory> pmsCategory);





}

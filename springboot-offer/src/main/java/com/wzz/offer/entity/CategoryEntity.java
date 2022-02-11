package com.wzz.offer.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 商品三级分类
 *
 * @author wzz
 * @email 3325667808@qq.com
 * @date 2022-02-11 14:40:21
 */
@Data
@TableName("pms_category")
public class CategoryEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 分类id
     */
    @TableId
    @ExcelProperty(value = "分类id", index = 0)
    private Long catId;
    /**
     * 分类名称
     */
    @ExcelProperty(value = "分类名称", index = 1)
    private String name;
    /**
     * 父分类id
     */
    @ExcelProperty(value = "父分类id", index = 2)
    private Long parentCid;
    /**
     * 层级
     */
    @ExcelProperty(value = "层级", index = 3)
    private Integer catLevel;
    /**
     * 是否显示[0-不显示，1显示]
     */
    @ExcelProperty(value = "是否显示[0-不显示，1显示]", index = 4)
    private Integer showStatus;
    /**
     * 排序
     */
    @ExcelProperty(value = "排序", index = 5)
    private Integer sort;
    /**
     * 图标地址
     */
    @ExcelProperty(value = "图标地址", index = 6)
    private String icon;
    /**
     * 计量单位
     */
    @ExcelProperty(value = "计量单位", index = 7)
    private String productUnit;
    /**
     * 商品数量
     */
    @ExcelProperty(value = "商品数量", index = 8)
    private Integer productCount;

}

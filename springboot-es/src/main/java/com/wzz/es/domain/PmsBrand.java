package com.wzz.es.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 品牌对象 pms_brand
 * 
 * @author ruoyi
 * @date 2022-05-22
 */
public class PmsBrand
{
    private static final long serialVersionUID = 1L;

    /** 品牌id */
    private Long brandId;

    /** 品牌名 */
    //@Excel(name = "品牌名")
    private String name;

    /** 品牌logo地址 */
    //@Excel(name = "品牌logo地址")
    private String logo;

    /** 介绍 */
    //@Excel(name = "介绍")
    private String descript;

    /** 显示状态[0-不显示；1-显示] */
    //@Excel(name = "显示状态[0-不显示；1-显示]")
    private Integer showStatus;

    /** 检索首字母 */
    //@Excel(name = "检索首字母")
    private String firstLetter;

    /** 排序 */
    //@Excel(name = "排序")
    private Long sort;

    /** 三级分类id */
    //@Excel(name = "三级分类id")
    private Long catId;

    public void setBrandId(Long brandId) 
    {
        this.brandId = brandId;
    }

    public Long getBrandId() 
    {
        return brandId;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setLogo(String logo) 
    {
        this.logo = logo;
    }

    public String getLogo() 
    {
        return logo;
    }
    public void setDescript(String descript) 
    {
        this.descript = descript;
    }

    public String getDescript() 
    {
        return descript;
    }
    public void setShowStatus(Integer showStatus) 
    {
        this.showStatus = showStatus;
    }

    public Integer getShowStatus() 
    {
        return showStatus;
    }
    public void setFirstLetter(String firstLetter) 
    {
        this.firstLetter = firstLetter;
    }

    public String getFirstLetter() 
    {
        return firstLetter;
    }
    public void setSort(Long sort) 
    {
        this.sort = sort;
    }

    public Long getSort() 
    {
        return sort;
    }
    public void setCatId(Long catId) 
    {
        this.catId = catId;
    }

    public Long getCatId() 
    {
        return catId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("brandId", getBrandId())
            .append("name", getName())
            .append("logo", getLogo())
            .append("descript", getDescript())
            .append("showStatus", getShowStatus())
            .append("firstLetter", getFirstLetter())
            .append("sort", getSort())
            .append("catId", getCatId())
            .toString();
    }
}

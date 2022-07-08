package com.wzz.util.es;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 *es中保存商品的数据模型
 */
@Data
public class SkuEsModel {
    private Long spuId;
    private Long skuId;
    private String skuTitle;
    private BigDecimal skuPrice;
    private String skuImg;

    private Long saleCount;
    private Boolean hosStock;
    private Long hosScore;
    private Long brandId;
    private Long catalogId;
    private String brandName;
    private String brandImg;
    private String catalogName;
    private List<Attrs> attrs;

    @Data
    public static class Attrs {
        private Long attrId;
        private String attrName;
        private String attrValue;
    }
}

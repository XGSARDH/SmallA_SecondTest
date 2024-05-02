package com.smalla.config;

/**
 * 搜索条件
 */
public enum ConditionType {
    /**
     * 全部商品
     */
    NULL,
    /**
     * 商品名字(相似查找)
     */
    LIKENAME,
    /**
     * 商品分类
     */
    CATEGORY,
    /**
     * 商户(店铺)
     */
    MERCHANT
}

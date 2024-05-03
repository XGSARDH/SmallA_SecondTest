package com.smalla.config;

/**
 * 标志订单状态
 */
public enum OrderStatus {
    /**
     * 交易进行中
     */
    DOING,
    /**
     * 申请退货中
     * 商家一定要查询是否存在资金流
     */
    APPLY_RETURN,
    /**
     * 确定退货
     */
    SURE_RETURN,
    /**
     * 等待收货, 金额已经暂存到订单中
     */
    WAITING_FOR_RECEIPT,
    /**
     * 完成交易
     */
    SOLVE

}

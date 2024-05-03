package com.smalla.config;

/**
 * 资金流状态
 * @author Sardh
 */
public enum FundFlowStatus {
    /**
     * 资金在订单中
     */
    IN_ORDER,
    /**
     * 资金交易完成, 已付给收款者
     */
    SOLVE,
    /**
     * 退回付款者
     */
    RETURN_TO_ACTIVE,
    /**
     * 等待管理员处理
     */
    WAIT_FOR_ADMIN,
    /**
     * 管理员处理为拒绝
     */
    CANCELLATION_FOR_ADMIN

}

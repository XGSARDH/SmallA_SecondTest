package com.smalla.po;

import java.math.BigDecimal;

public class FundFlow {
    private int fundFlowId;
    private int orderId;
    private int activeId;
    private int passiveId;
    private BigDecimal amount;
    private String changeType;
    private String fundflowStatus;

    public FundFlow() {
    }

    public FundFlow(int fundFlowId, int orderId, int activeId, int passiveId, BigDecimal amount, String changeType, String fundflowStatus) {
        this.fundFlowId = fundFlowId;
        this.orderId = orderId;
        this.activeId = activeId;
        this.passiveId = passiveId;
        this.amount = amount;
        this.changeType = changeType;
        this.fundflowStatus = fundflowStatus;
    }

    public int getFundFlowId() {
        return fundFlowId;
    }

    public void setFundFlowId(int fundFlowId) {
        this.fundFlowId = fundFlowId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getActiveId() {
        return activeId;
    }

    public void setActiveId(int activeId) {
        this.activeId = activeId;
    }

    public int getPassiveId() {
        return passiveId;
    }

    public void setPassiveId(int passiveId) {
        this.passiveId = passiveId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    public String getFundflowStatus() {
        return fundflowStatus;
    }

    public void setFundflowStatus(String fundflowStatus) {
        this.fundflowStatus = fundflowStatus;
    }
}

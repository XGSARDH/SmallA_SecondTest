package com.smalla.po;

/**
 * @author Sardh
 */
public class Fund {
    private int fundId;
    private int userId;
    private String totalFunds;
    private String availableFunds;
    private String frozenFunds;
    private int fundHealth;

    public Fund() {
    }

    public int getFundId() {
        return fundId;
    }

    public void setFundId(int fundId) {
        this.fundId = fundId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTotalFunds() {
        return totalFunds;
    }

    public void setTotalFunds(String totalFunds) {
        this.totalFunds = totalFunds;
    }

    public String getAvailableFunds() {
        return availableFunds;
    }

    public void setAvailableFunds(String availableFunds) {
        this.availableFunds = availableFunds;
    }

    public String getFrozenFunds() {
        return frozenFunds;
    }

    public void setFrozenFunds(String frozenFunds) {
        this.frozenFunds = frozenFunds;
    }

    public int getFundHealth() {
        return fundHealth;
    }

    public void setFundHealth(int fundHealth) {
        this.fundHealth = fundHealth;
    }
}

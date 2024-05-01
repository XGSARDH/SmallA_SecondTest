package com.smalla.po;

/**
 * @author Sardh
 */
public class Merchant {
    private int merchantId;
    private int userId;
    private int fundId;
    private String merchantName;
    private int merchantHealth;

    public Merchant() {
    }

    public int getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFundId() {
        return fundId;
    }

    public void setFundId(int fundId) {
        this.fundId = fundId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public int getMerchantHealth() {
        return merchantHealth;
    }

    public void setMerchantHealth(int merchantHealth) {
        this.merchantHealth = merchantHealth;
    }
}

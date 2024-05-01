package com.smalla.po;

/**
 * @author Sardh
 */
public class User {
    private int userId;
    private int fundId;
    private String username;
    private String password;
    private String phone;
    private int userHealth;
    private int isMerchant;
    private String defaultAddresss;

    public User() {
    }

    public String getDefaultAddresss() {
        return defaultAddresss;
    }

    public void setDefaultAddresss(String defaultAddresss) {
        this.defaultAddresss = defaultAddresss;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getUserHealth() {
        return userHealth;
    }

    public void setUserHealth(int userHealth) {
        this.userHealth = userHealth;
    }

    public int getIsMerchant() {
        return isMerchant;
    }

    public void setIsMerchant(int isMerchant) {
        this.isMerchant = isMerchant;
    }
}

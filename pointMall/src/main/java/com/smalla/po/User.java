package com.smalla.po;

import java.util.Date;

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
    private String defaultAddress;
    private Date lastCheckInDate;

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", fundId=" + fundId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", userHealth=" + userHealth +
                ", isMerchant=" + isMerchant +
                ", defaultAddress='" + defaultAddress + '\'' +
                ", lastCheckInDate=" + lastCheckInDate +
                '}';
    }

    public Date getLastCheckInDate() {
        return lastCheckInDate;
    }

    public void setLastCheckInDate(Date lastCheckInDate) {
        this.lastCheckInDate = lastCheckInDate;
    }

    public String getDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(String defaultAddresss) {
        this.defaultAddress = defaultAddresss;
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

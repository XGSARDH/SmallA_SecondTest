package com.smalla.po;

/**
 * @author Sardh
 */
public class Product {
    private int productId;
    private int merchantId;
    private String productName;
    private int categoryId;
    private String price;
    private String stock;
    private int productHealth;
    private int salesVolume;
    private int positiveReviews;

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", merchantId=" + merchantId +
                ", productName='" + productName + '\'' +
                ", categoryId=" + categoryId +
                ", price='" + price + '\'' +
                ", stock='" + stock + '\'' +
                ", productHealth=" + productHealth +
                ", salesVolume=" + salesVolume +
                ", positiveReviews=" + positiveReviews +
                '}';
    }

    public Product() {
    }

    public int getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(int salesVolume) {
        this.salesVolume = salesVolume;
    }

    public int getPositiveReviews() {
        return positiveReviews;
    }

    public void setPositiveReviews(int positiveReviews) {
        this.positiveReviews = positiveReviews;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public int getProductHealth() {
        return productHealth;
    }

    public void setProductHealth(int productHealth) {
        this.productHealth = productHealth;
    }
}

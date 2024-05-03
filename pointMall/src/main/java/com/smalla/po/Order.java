package com.smalla.po;

/**
 * @author Sardh
 */
public class Order {
    private int orderId;
    private int activeId;
    private int passiveId;
    private int productId;
    private int productNumber;
    private String productUnitPrice;
    private String productTotalPrice;
    private String description;
    /**
     * 1: 代表交易中
     * 2. 代表申请退货
     * 3. 代表已退货
     * 4. 代表完成订单
     */
    private String orderStatus;

    public Order() {
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

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(int productNumber) {
        this.productNumber = productNumber;
    }

    public String getProductUnitPrice() {
        return productUnitPrice;
    }

    public void setProductUnitPrice(String productUnitPrice) {
        this.productUnitPrice = productUnitPrice;
    }

    public String getProductTotalPrice() {
        return productTotalPrice;
    }

    public void setProductTotalPrice(String productTotalPrice) {
        this.productTotalPrice = productTotalPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}

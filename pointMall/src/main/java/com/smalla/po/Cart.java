package com.smalla.po;

/**
 * @author Sardh
 */
public class Cart {
    private int cartId;
    private int userId;
    private int productId;
    private int productQuantity;
    /**
     * 0: 代表被废弃
     * 1: 代表可用
     * 2: 代表已生成订单
     */
    private int cartHealth;

    public Cart() {
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", userId=" + userId +
                ", productId=" + productId +
                ", productQuantity=" + productQuantity +
                ", cartHealth=" + cartHealth +
                '}';
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public int getCartHealth() {
        return cartHealth;
    }

    public void setCartHealth(int cartHealth) {
        this.cartHealth = cartHealth;
    }
}
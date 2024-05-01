package com.smalla.po;

/**
 * @author Sardh
 */
public class Cart {
    private int cartId;
    private int userId;
    private int productId;
    private int productQuantity;
    private int cartHealth;

    public Cart() {
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
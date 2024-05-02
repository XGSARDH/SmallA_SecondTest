package com.smalla.po;

public class Comment {
    private int commentId;
    private int userId;
    private int orderId;
    private int productId;
    private int merchantId;
    private String commentDetail;

    public Comment() {
    }

    public Comment(int commentId, int userId, int orderId, int productId, int merchantId, String commentDetail) {
        this.commentId = commentId;
        this.userId = userId;
        this.orderId = orderId;
        this.productId = productId;
        this.merchantId = merchantId;
        this.commentDetail = commentDetail;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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

    public String getCommentDetail() {
        return commentDetail;
    }

    public void setCommentDetail(String commentDetail) {
        this.commentDetail = commentDetail;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", userId=" + userId +
                ", orderId=" + orderId +
                ", productId=" + productId +
                ", merchantId=" + merchantId +
                ", commentDetail='" + commentDetail + '\'' +
                '}';
    }
}

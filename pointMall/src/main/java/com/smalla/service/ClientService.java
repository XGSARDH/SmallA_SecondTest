package com.smalla.service;

public interface ClientService {
    /**
     * 查看个人信息
     * @param userId
     * @return
     */
    String viewUserInformation(int userId);

    /**
     * 修改个人信息
     * @param userId
     * @param username
     * @param phone
     * @param password
     * @return
     */
    String updateUserInformation(int userId, String username, String phone, String password);

    /**
     * 查看个人地址信息
     * @param userId
     * @return
     */
    String viewUserAddress(int userId);

    /**
     * 修改个人地址
     * @param addressId
     * @param addressDetail
     * @param phone
     * @return
     */
    String updateUserAddress(int addressId, String addressDetail, String phone);

    /**
     * 增加个人地址
     * @param userId
     * @param addressDetail
     * @param phone
     * @return
     */
    String insertUserAddress(int userId, String addressDetail, String phone);

    /**
     * 设置默认地址
     * @param userId
     * @param addressId
     * @return
     */
    String setDefaultAddress(int userId, int addressId);

    /**
     * 将商品添加到购物车
     * @param userId
     * @param productId
     * @param productQuantity
     * @return
     */
    String addProductToCart(int userId, int productId, int productQuantity);

    /**
     * 查看个人购物车
     * @param userId
     * @return
     */
    public String viewUserCart(int userId) ;

    /**
     * 删除个人购物车, 注意只是作废该购物车, 后续可以查找记录
     * @param cartId
     * @param userId
     * @return
     */
    String deleteProductFromCart(int cartId, int userId);

    /**
     * 修改个人购物车
     * @param cartId
     * @param userId
     * @param productQuantity
     * @return
     */
    String updateProductFromCart(int cartId, int userId, int productQuantity);

    /**
     * 结算个人全部购物车
     * @param userId
     * @return
     */
    String settlementUserAllCart(int userId);

    /**
     * 查看个人正在进行订单
     * @param userId
     * @return
     */
    String viewUserOrder(int userId);

    /**
     * 申请退回订单
     * @param orderId
     * @param userId
     * @return
     */
    String cancellationUserOrder(int orderId, int userId);

    /**
     * 支付订单
     * @param orderId
     * @param userId
     * @return
     */
    String paymentOrder(int orderId, int userId);

    /**
     * 查看个人转出资金流
     * @param userId
     * @return
     */
    String viewUserActiveFundFlows(int userId);

    /**
     * 查看个人转入资金流
     * @param userId
     * @return
     */
    String viewUserPassiveFundFlows(int userId);

    /**
     * 对订单进行评价
     * @param userId
     * @param orderId
     * @param commentDetail
     * @param isPositive
     * @return
     */
    String conductComment(int userId, int orderId, String commentDetail, int isPositive);

    /**
     * 查看个人对该订单评价
     * @param userId
     * @param orderId
     * @return
     */
    String viewUserCommentForOrder(int userId, int orderId);

    /**
     * 发起充值请求
     * @param userId
     * @param amount
     * @return
     */
    String applyToIncreasePoints(int userId, String amount);
}

package com.smalla.service;

public interface MerchantService {
    /**
     * 增加商户商品
     * @param merchantId
     * @param productName
     * @param categoryId
     * @param price
     * @param stock
     * @param productHealth
     * @return
     */
    String insertMerchantProduct(int merchantId, String productName, int categoryId, String price, String stock, int productHealth);

    /**
     * 查看商户商品
     * @param merchantId
     * @return
     */
    String viewMerchantProduct(int merchantId);

    /**
     * 更新商户商品
     * @param productId
     * @param merchantId
     * @param productName
     * @param categoryId
     * @param price
     * @param stock
     * @param productHealth
     * @return
     */
    String updateProduct(int productId, int merchantId, String productName, int categoryId, String price, String stock, int productHealth);

    /**
     * 查看商户信息
     * @param merchantId
     * @return
     */
    String viewMerchantInformation(int merchantId);

    /**
     * 修改商户信息
     * @param merchantId
     * @param merchantName
     * @return
     */
    String updateMerchantInformation(int merchantId, String merchantName);

    /**
     * 查看退货请求
     * @param merchantId
     * @param userId
     * @return
     */
    String viewCancellationOrder(int merchantId, int userId);

    /**
     * 同意退货请求
     * @param orderId
     * @param merchantId
     * @param userId
     * @return
     */
    String agreeCancellationOrder(int orderId, int merchantId, int userId);

    /**
     * 拒绝退货请求
     * @param orderId
     * @param merchantId
     * @param userId
     * @return
     */
    String disagreeCancellationOrder(int orderId, int merchantId, int userId);
}

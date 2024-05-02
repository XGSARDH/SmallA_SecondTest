package com.smalla.service;

public interface TouristService {
    /**
     * 登录功能
     * @param loginMode
     * @param loginCredentials
     * @param password
     * @return
     */
    public String login(String loginMode, String loginCredentials, String password);

    /**
     * 注册功能
     * @param username
     * @param password
     * @param phone
     * @param defaultAddress
     * @return
     */
    public String register(String username, String password, String phone, String defaultAddress);

    /**
     * 根据检索条件和搜索条件返回商品详情
     * @param conditionType
     * @param conditionDetail
     * @param sortType
     * @return
     */
    public String viewProductByConditionAndSorting(String conditionType, String conditionDetail, String sortType, int start, int end);

    /**
     * 查看商品详情
     * @param productId
     * @return
     */
    String viewProductDetails(int productId);

}
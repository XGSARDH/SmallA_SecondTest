package com.smalla.service;

public interface AdminService {
    /**
     * 查看所有客户端信息
     * @return
     */
    String viewUserByAdmin();

    /**
     * 修改指定客户端信息
     * @param userId
     * @return
     */
    String updateUserByAdmin(int userId, String username, String password, String phone);

    /**
     * 查看所有商户端信息
     * @return
     */
    String viewMerchantByAdmin();

    /**
     * 修改指定商户端信息
     * @return
     */
    public String updateMerchantByAdmin(int merchantId, String merchantName, int merchantHealth);

    /**
     * 查看所有商品信息
     * @return
     */
    String viewProductByAdmin();

    /**
     *修改指定商品信息
     * @return
     */
    public String updateProductByAdmin(int productId, int productHealth);

    /**
     * 查看所有商品分类信息
     * @return
     */
    String viewCategoryByAdmin();

    /**
     * 增加商品分类信息
     * @return
     */
    public String insertCategoryByAdmin(String category);

    /**
     * 查看所有签到天数对应的签到人数
     * @return
     */
    String viewDailycheckinByAdmin();
}

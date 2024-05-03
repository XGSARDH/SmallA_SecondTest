package com.smalla.dao;

import com.smalla.po.Merchant;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Sardh
 */
public interface MerchantDao {
    /**
     * 根据用户ID来查找
     * @param userId
     * @return
     */
    List<Merchant> listByUserId(int userId);

    /**
     * 根据商户ID来查找
     * @param merchantId
     * @return
     */
    List<Merchant> listByMerchantId(int merchantId);

    /**
     * 根据商户ID来查找
     * @param merchantId
     * @return
     */
    Merchant getByMerchantId(int merchantId);

    /**
     * 查找全部
     * @return
     */
    List<Merchant> listAll();


    /**
     * 保存
     * @param merchant
     * @return
     * @throws SQLException
     */
    Integer save(Merchant merchant) throws SQLException;

    /**
     * 更新一个条目
     * @param merchant
     * @throws SQLException
     */
    void update(Merchant merchant) throws SQLException;

    /**
     * 删除一个条目
     * @param merchantId
     * @throws SQLException
     */
    void delete(int merchantId) throws SQLException;
}

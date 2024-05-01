package com.smalla.dao;

import com.smalla.po.Product;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Sardh
 */
public interface ProductDao {
    /**
     * 根据商户ID来查找
     * @param merchantId
     * @return
     */
    List<Product> findByMerchantId(int merchantId);

    /**
     * 保存
     * @param product
     * @return
     * @throws SQLException
     */
    Integer save(Product product) throws SQLException;

    /**
     * 更新一个条目
     * @param product
     * @throws SQLException
     */
    void update(Product product) throws SQLException;

    /**
     * 删除一个条目
     * @param productId
     * @throws SQLException
     */
    void delete(int productId) throws SQLException;
}

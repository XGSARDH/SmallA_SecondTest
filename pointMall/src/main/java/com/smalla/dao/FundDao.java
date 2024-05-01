package com.smalla.dao;

import com.smalla.po.Fund;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Sardh
 */
public interface FundDao {
    /**
     * 根据用户ID来查找
     * @param userId
     * @return
     */
    List<Fund> findByUserId(int userId);

    /**
     * 保存
     * @param fund
     * @return
     * @throws SQLException
     */
    Integer save(Fund fund) throws SQLException;

    /**
     * 更新一个条目
     * @param fund
     * @throws SQLException
     */
    void update(Fund fund) throws SQLException;

    /**
     * 删除一个条目
     * @param fundId
     * @throws SQLException
     */
    void delete(int fundId) throws SQLException;
}

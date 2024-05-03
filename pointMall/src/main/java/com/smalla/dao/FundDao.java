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
    List<Fund> listByUserId(int userId);

    /**
     * 根据用户ID来查找
     * @param userId
     * @return
     */
    Fund getByUserId(int userId);

    /**
     * 根据账户ID来查找
     * @param fundId
     * @return
     */
    List<Fund> listByFundId(int fundId);

    /**
     * 根据账户ID来查找
     * @param fundId
     * @return
     */
    Fund getByFundId(int fundId);

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

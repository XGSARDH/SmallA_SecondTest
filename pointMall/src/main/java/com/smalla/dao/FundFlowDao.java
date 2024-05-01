package com.smalla.dao;

import com.smalla.po.FundFlow;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Sardh
 */
public interface FundFlowDao {
    /**
     * 查询全部
     * @return
     */
    List<FundFlow> findAll();

    /**
     * 保存一条新条目并返回ID
     * @param fundFlow
     * @return
     * @throws SQLException
     */
    Integer save(FundFlow fundFlow) throws SQLException;

    /**
     * 更新
     * @param fundFlow
     * @throws SQLException
     */
    void update(FundFlow fundFlow) throws SQLException;

    /**
     * 删除
     * @param fundFlowId
     * @throws SQLException
     */
    void delete(int fundFlowId) throws SQLException;

    /**
     * 根据账单ID来查找对应的条目
     * @param orderIdToFind
     * @return
     */
    List<FundFlow> findByOrderId(int orderIdToFind);
}

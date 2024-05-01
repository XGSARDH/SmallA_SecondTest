package com.smalla.dao.impl;

import com.smalla.dao.FundFlowDao;
import com.smalla.po.FundFlow;
import com.smalla.util.connectPool.ConnectionPoolManager;
import com.smalla.util.crudUtils.CRUDUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FundFlowDaoImpl implements FundFlowDao {

    private static volatile FundFlowDaoImpl instance;

    public static FundFlowDaoImpl getInstance() {
        if (instance == null) {
            synchronized (FundFlowDaoImpl.class) {
                if (instance == null) {
                    instance = new FundFlowDaoImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public List<FundFlow> findAll() {
        String sql = "SELECT `fund_flow_id`, `order_id`, `active_id`, `passive_id`, `amount`, `change_type`, `fundflow_status` FROM `fund_flows`";
        Connection connection = ConnectionPoolManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet rs = CRUDUtils.query(sql, connection, preparedStatement);
        List<FundFlow> fundFlows = new ArrayList<>();

        try {
            while (rs.next()) {
                FundFlow fundFlow = new FundFlow();
                fundFlow.setFundFlowId(rs.getInt("fund_flow_id"));
                fundFlow.setOrderId(rs.getInt("order_id"));
                fundFlow.setActiveId(rs.getInt("active_id"));
                fundFlow.setPassiveId(rs.getInt("passive_id"));
                fundFlow.setAmount(rs.getBigDecimal("amount"));
                fundFlow.setChangeType(rs.getString("change_type"));
                fundFlow.setFundflowStatus(rs.getString("fundflow_status"));
                fundFlows.add(fundFlow);
            }
            if (rs != null) {
                rs.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            ConnectionPoolManager.releaseConnection(connection);
            return fundFlows;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer save(FundFlow fundFlow) throws SQLException {
        String sql = "INSERT INTO `fund_flows` (`order_id`, `active_id`, `passive_id`, `amount`, `change_type`, `fundflow_status`) VALUES (?, ?, ?, ?, ?, ?)";
        return CRUDUtils.save(sql, fundFlow.getOrderId(), fundFlow.getActiveId(), fundFlow.getPassiveId(), fundFlow.getAmount(), fundFlow.getChangeType(), fundFlow.getFundflowStatus());
    }

    @Override
    public void update(FundFlow fundFlow) throws SQLException {
        String sql = "UPDATE `fund_flows` SET `order_id` = ?, `active_id` = ?, `passive_id` = ?, `amount` = ?, `change_type` = ?, `fundflow_status` = ? WHERE `fund_flow_id` = ?";
        CRUDUtils.update(sql, fundFlow.getOrderId(), fundFlow.getActiveId(), fundFlow.getPassiveId(), fundFlow.getAmount(), fundFlow.getChangeType(), fundFlow.getFundflowStatus(), fundFlow.getFundFlowId());
    }

    @Override
    public void delete(int fundFlowId) throws SQLException {
        String sql = "DELETE FROM `fund_flows` WHERE `fund_flow_id` = ?";
        CRUDUtils.update(sql, fundFlowId);
    }

    @Override
    public List<FundFlow> findByOrderId(int orderIdToFind) {
        String sql = "SELECT `fund_flow_id`, `order_id`, `active_id`, `passive_id`, `amount`, `change_type`, `fundflow_status` FROM `fund_flows` WHERE `order_id` = ?";
        Connection connection = ConnectionPoolManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet rs = CRUDUtils.query(sql, connection, preparedStatement, orderIdToFind);
        List<FundFlow> fundFlows = new ArrayList<>();

        try {
            while (rs.next()) {
                FundFlow fundFlow = new FundFlow();
                fundFlow.setFundFlowId(rs.getInt("fund_flow_id"));
                fundFlow.setOrderId(rs.getInt("order_id"));
                fundFlow.setActiveId(rs.getInt("active_id"));
                fundFlow.setPassiveId(rs.getInt("passive_id"));
                fundFlow.setAmount(rs.getBigDecimal("amount"));
                fundFlow.setChangeType(rs.getString("change_type"));
                fundFlow.setFundflowStatus(rs.getString("fundflow_status"));
                fundFlows.add(fundFlow);
            }
            if (rs != null) {
                rs.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            ConnectionPoolManager.releaseConnection(connection);
            return fundFlows;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

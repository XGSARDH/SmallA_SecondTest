package com.smalla.dao.impl;

import com.smalla.dao.FundDao;
import com.smalla.po.Fund;
import com.smalla.util.connectPool.ConnectionPoolManager;
import com.smalla.util.crudUtils.CRUDUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sardh
 */
public class FundDaoImpl implements FundDao {

    private static volatile FundDaoImpl instance;

    public static FundDaoImpl getInstance() {
        if (instance == null) {
            synchronized (FundDaoImpl.class) {
                if (instance == null) {
                    instance = new FundDaoImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public List<Fund> listByUserId(int userId) {
        String sql = "SELECT `fund_id`, `user_id`, `total_funds`, `available_funds`, `frozen_funds`, `fund_health` FROM `funds` WHERE `user_id` = ?";
        Connection connection = ConnectionPoolManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet rs = CRUDUtils.query(sql, connection, preparedStatement, userId);
        List<Fund> funds = new ArrayList<>();

        try {
            while (rs.next()) {
                Fund fund = new Fund();
                fund.setFundId(rs.getInt("fund_id"));
                fund.setUserId(rs.getInt("user_id"));
                fund.setTotalFunds(rs.getString("total_funds"));
                fund.setAvailableFunds(rs.getString("available_funds"));
                fund.setFrozenFunds(rs.getString("frozen_funds"));
                fund.setFundHealth(rs.getInt("fund_health"));
                funds.add(fund);
            }
            if(rs != null) {
                rs.close();
            }
            if(preparedStatement != null) {
                preparedStatement.close();
            }
            ConnectionPoolManager.releaseConnection(connection);
            return funds;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Fund getByUserId(int userId) {
        List<Fund> funds = listByUserId(userId);
        if (funds == null || funds.isEmpty()) {
            return null;
        }else {
            return funds.get(0);
        }
    }

    @Override
    public List<Fund> listByFundId(int foudId){
        String sql = "SELECT `fund_id`, `user_id`, `total_funds`, `available_funds`, `frozen_funds`, `fund_health` FROM `funds` WHERE `fund_id` = ?";
        Connection connection = ConnectionPoolManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet rs = CRUDUtils.query(sql, connection, preparedStatement, foudId);
        List<Fund> funds = new ArrayList<>();

        try {
            while (rs.next()) {
                Fund fund = new Fund();
                fund.setFundId(rs.getInt("fund_id"));
                fund.setUserId(rs.getInt("user_id"));
                fund.setTotalFunds(rs.getString("total_funds"));
                fund.setAvailableFunds(rs.getString("available_funds"));
                fund.setFrozenFunds(rs.getString("frozen_funds"));
                fund.setFundHealth(rs.getInt("fund_health"));
                funds.add(fund);
            }
            if(rs != null) {
                rs.close();
            }
            if(preparedStatement != null) {
                preparedStatement.close();
            }
            ConnectionPoolManager.releaseConnection(connection);
            return funds;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Fund getByFundId(int fundId) {
        List<Fund> funds = listByFundId(fundId);
        if (funds == null || funds.isEmpty()) {
            return null;
        }else {
            return funds.get(0);
        }
    }

    @Override
    public Integer save(Fund fund) throws SQLException {
        String sql = "INSERT INTO `funds` (`user_id`, `total_funds`, `available_funds`, `frozen_funds`, `fund_health`) VALUES (?, ?, ?, ?, ?)";
        return CRUDUtils.save(sql, fund.getUserId(), fund.getTotalFunds(), fund.getAvailableFunds(), fund.getFrozenFunds(), fund.getFundHealth());
    }

    @Override
    public void update(Fund fund) throws SQLException {
        String sql = "UPDATE `funds` SET `total_funds` = ?, `available_funds` = ?, `frozen_funds` = ?, `fund_health` = ? WHERE `fund_id` = ?";
        CRUDUtils.update(sql, fund.getTotalFunds(), fund.getAvailableFunds(), fund.getFrozenFunds(), fund.getFundHealth(), fund.getFundId());
    }

    @Override
    public void delete(int fundId) throws SQLException {
        String sql = "DELETE FROM `funds` WHERE `fund_id` = ?";
        CRUDUtils.update(sql, fundId);
    }
}

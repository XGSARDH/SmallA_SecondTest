package com.smalla.dao.impl;

import com.smalla.dao.MerchantDao;
import com.smalla.po.Merchant;
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
public class MerchantDaoImpl implements MerchantDao {

    private static volatile MerchantDaoImpl instance;

    public static MerchantDaoImpl getInstance() {
        if (instance == null) {
            synchronized (MerchantDaoImpl.class) {
                if (instance == null) {
                    instance = new MerchantDaoImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public List<Merchant> listByUserId(int userId) {
        String sql = "SELECT `merchant_id`, `user_id`, `fund_id`, `merchant_name`, `merchant_health` FROM `merchants` WHERE `user_id` = ?";
        Connection connection = ConnectionPoolManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet rs = CRUDUtils.query(sql, connection, preparedStatement, userId);
        List<Merchant> merchants = new ArrayList<>();

        try {
            while (rs.next()) {
                Merchant merchant = new Merchant();
                merchant.setMerchantId(rs.getInt("merchant_id"));
                merchant.setUserId(rs.getInt("user_id"));
                merchant.setFundId(rs.getInt("fund_id"));
                merchant.setMerchantName(rs.getString("merchant_name"));
                merchant.setMerchantHealth(rs.getInt("merchant_health"));
                merchants.add(merchant);
            }
            if(rs != null) {
                rs.close();
            }
            if(preparedStatement != null) {
                preparedStatement.close();
            }
            ConnectionPoolManager.releaseConnection(connection);
            return merchants;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Merchant> listByMerchantId(int merchantId){
        String sql = "SELECT `merchant_id`, `user_id`, `fund_id`, `merchant_name`, `merchant_health` FROM `merchants` WHERE `merchant_id` = ?";
        Connection connection = ConnectionPoolManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet rs = CRUDUtils.query(sql, connection, preparedStatement, merchantId);
        List<Merchant> merchants = new ArrayList<>();

        try {
            while (rs.next()) {
                Merchant merchant = new Merchant();
                merchant.setMerchantId(rs.getInt("merchant_id"));
                merchant.setUserId(rs.getInt("user_id"));
                merchant.setFundId(rs.getInt("fund_id"));
                merchant.setMerchantName(rs.getString("merchant_name"));
                merchant.setMerchantHealth(rs.getInt("merchant_health"));
                merchants.add(merchant);
            }
            if(rs != null) {
                rs.close();
            }
            if(preparedStatement != null) {
                preparedStatement.close();
            }
            ConnectionPoolManager.releaseConnection(connection);
            return merchants;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Merchant> listAll() {
        String sql = "SELECT `merchant_id`, `user_id`, `fund_id`, `merchant_name`, `merchant_health` FROM `merchants`";
        Connection connection = ConnectionPoolManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet rs = CRUDUtils.query(sql, connection, preparedStatement);
        List<Merchant> merchants = new ArrayList<>();

        try {
            while (rs.next()) {
                Merchant merchant = new Merchant();
                merchant.setMerchantId(rs.getInt("merchant_id"));
                merchant.setUserId(rs.getInt("user_id"));
                merchant.setFundId(rs.getInt("fund_id"));
                merchant.setMerchantName(rs.getString("merchant_name"));
                merchant.setMerchantHealth(rs.getInt("merchant_health"));
                merchants.add(merchant);
            }
            if(rs != null) {
                rs.close();
            }
            if(preparedStatement != null) {
                preparedStatement.close();
            }
            ConnectionPoolManager.releaseConnection(connection);
            return merchants;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer save(Merchant merchant) throws SQLException {
        String sql = "INSERT INTO `merchants` (`user_id`, `fund_id`, `merchant_name`, `merchant_health`) VALUES (?, ?, ?, ?)";
        return CRUDUtils.save(sql, merchant.getUserId(), merchant.getFundId(), merchant.getMerchantName(), merchant.getMerchantHealth());
    }

    @Override
    public void update(Merchant merchant) throws SQLException {
        String sql = "UPDATE `merchants` SET `merchant_name` = ?, `merchant_health` = ? WHERE `merchant_id` = ?";
        CRUDUtils.update(sql, merchant.getMerchantName(), merchant.getMerchantHealth(), merchant.getMerchantId());
    }

    @Override
    public void delete(int merchantId) throws SQLException {
        String sql = "DELETE FROM `merchants` WHERE `merchant_id` = ?";
        CRUDUtils.update(sql, merchantId);
    }
}

package com.smalla.dao.impl;

import com.smalla.dao.UserDao;
import com.smalla.po.User;
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
public class UserDaoImpl implements UserDao {

    private static volatile UserDaoImpl instance;

    public static UserDaoImpl getInstance() {
        if (instance == null) {
            synchronized (UserDaoImpl.class) {
                if (instance == null) {
                    instance = new UserDaoImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public List<User> listById(int userId) {
        String sql = "SELECT `user_id`, `fund_id`, `username`, `password`, `phone`, `user_health`, `is_merchant`, `default_address`, `lastCheckInDate` FROM `users` WHERE `user_id` = ?";
        Connection connection = ConnectionPoolManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet rs = CRUDUtils.query(sql, connection, preparedStatement, userId);
        List<User> users = new ArrayList<>();
        try {
            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setFundId(rs.getInt("fund_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setPhone(rs.getString("phone"));
                user.setUserHealth(rs.getInt("user_health"));
                user.setIsMerchant(rs.getInt("is_merchant"));
                user.setDefaultAddress(rs.getString("default_address"));
                user.setLastCheckInDate(rs.getDate("lastCheckInDate"));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            ConnectionPoolManager.releaseConnection(connection);
        }
    }
    @Override
    public List<User> listByPhone(String phone) {
        String sql = "SELECT `user_id`, `fund_id`, `username`, `password`, `phone`, `user_health`, `is_merchant`, `default_address`, `lastCheckInDate` FROM `users` WHERE `phone` = ?";
        Connection connection = ConnectionPoolManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet rs = CRUDUtils.query(sql, connection, preparedStatement, phone);
        List<User> users = new ArrayList<>();
        try {
            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setFundId(rs.getInt("fund_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setPhone(rs.getString("phone"));
                user.setUserHealth(rs.getInt("user_health"));
                user.setIsMerchant(rs.getInt("is_merchant"));
                user.setDefaultAddress(rs.getString("default_address"));
                user.setLastCheckInDate(rs.getDate("lastCheckInDate"));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            ConnectionPoolManager.releaseConnection(connection);
        }
    }

    @Override
    public List<User> listAll() {
        String sql = "SELECT `user_id`, `fund_id`, `username`, `password`, `phone`, `user_health`, `is_merchant`, `default_address`, `lastCheckInDate` FROM `users`";
        Connection connection = ConnectionPoolManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet rs = CRUDUtils.query(sql, connection, preparedStatement);
        List<User> users = new ArrayList<>();
        try {
            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setFundId(rs.getInt("fund_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setPhone(rs.getString("phone"));
                user.setUserHealth(rs.getInt("user_health"));
                user.setIsMerchant(rs.getInt("is_merchant"));
                user.setDefaultAddress(rs.getString("default_address"));
                user.setLastCheckInDate(rs.getDate("lastCheckInDate"));
                if(user.getUserId() != 0){
                    users.add(user);
                }
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            ConnectionPoolManager.releaseConnection(connection);
        }
    }

    @Override
    public Integer save(User user) throws SQLException {
        String sql = "INSERT INTO `users` (`fund_id`, `username`, `password`, `phone`, `user_health`, `is_merchant`, `default_address`, `lastCheckInDate`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return CRUDUtils.save(sql, user.getFundId(), user.getUsername(), user.getPassword(), user.getPhone(), user.getUserHealth(), user.getIsMerchant(), user.getDefaultAddress(), user.getLastCheckInDate());
    }

    @Override
    public void update(User user) throws SQLException {
        String sql = "UPDATE `users` SET `fund_id` = ?, `username` = ?, `password` = ?, `phone` = ?, `user_health` = ?, `is_merchant` = ?, `default_address` = ?, `lastCheckInDate` = ? WHERE `user_id` = ?";
        CRUDUtils.update(sql, user.getFundId(), user.getUsername(), user.getPassword(), user.getPhone(), user.getUserHealth(), user.getIsMerchant(), user.getDefaultAddress(), user.getLastCheckInDate(), user.getUserId());
    }

    @Override
    public void delete(int userId) throws SQLException {
        String sql = "DELETE FROM `users` WHERE `user_id` = ?";
        CRUDUtils.update(sql, userId);
    }
}

package com.smalla.dao.impl;

import com.smalla.dao.AddressDao;
import com.smalla.po.Address;
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
public class AddressDaoImpl implements AddressDao {

    private static volatile AddressDaoImpl instance;

    public static AddressDaoImpl getInstance() {
        if (instance == null) {
            synchronized (AddressDaoImpl.class) {
                if (instance == null) {
                    instance = new AddressDaoImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public List<Address> listByUserId(int userId) {
        String sql = "SELECT `address_id`, `user_id`, `phone`, `address` FROM `addresses` WHERE `user_id` = ?";
        Connection connection = ConnectionPoolManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet rs = CRUDUtils.query(sql, connection, preparedStatement, userId);
        List<Address> addresses = new ArrayList<>();

        try {
            while (rs.next()) {
                Address address = new Address();
                address.setAddressId(rs.getInt("address_id"));
                address.setUserId(rs.getInt("user_id"));
                address.setPhone(rs.getString("phone"));
                address.setAddress(rs.getString("address"));
                addresses.add(address);
            }
            if(rs != null) {
                rs.close();
            }
            if(preparedStatement != null) {
                preparedStatement.close();
            }
            ConnectionPoolManager.releaseConnection(connection);
            return addresses;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer save(Address address) throws SQLException {
        String sql = "INSERT INTO `addresses` (`user_id`, `phone`, `address`) VALUES (?, ?, ?)";
        return CRUDUtils.save(sql, address.getUserId(), address.getPhone(), address.getAddress());
    }

    @Override
    public void update(Address address) throws SQLException {
        String sql = "UPDATE `addresses` SET `phone` = ?, `address` = ? WHERE `address_id` = ?";
        CRUDUtils.update(sql, address.getPhone(), address.getAddress(), address.getAddressId());
    }

    @Override
    public void delete(int addressId) throws SQLException {
        String sql = "DELETE FROM `addresses` WHERE `address_id` = ?";
        CRUDUtils.update(sql, addressId);
    }
}

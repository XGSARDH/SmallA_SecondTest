package com.smalla.dao.impl;

import com.smalla.dao.CartDao;
import com.smalla.po.Cart;
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
public class CartDaoImpl implements CartDao {

    private static volatile CartDaoImpl instance;

    public static CartDaoImpl getInstance() {
        if (instance == null) {
            synchronized (CartDaoImpl.class) {
                if (instance == null) {
                    instance = new CartDaoImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public List<Cart> findByUserId(int userId) {
        String sql = "SELECT `cart_id`, `user_id`, `product_id`, `product_quantity`, `cart_health` FROM `carts` WHERE `user_id` = ?";
        Connection connection = ConnectionPoolManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet rs = CRUDUtils.query(sql, connection, preparedStatement, userId);
        List<Cart> carts = new ArrayList<>();

        try {
            while (rs.next()) {
                Cart cart = new Cart();
                cart.setCartId(rs.getInt("cart_id"));
                cart.setUserId(rs.getInt("user_id"));
                cart.setProductId(rs.getInt("product_id"));
                cart.setProductQuantity(rs.getInt("product_quantity"));
                cart.setCartHealth(rs.getInt("cart_health"));
                carts.add(cart);
            }
            if(rs != null) {
                rs.close();
            }
            if(preparedStatement != null) {
                preparedStatement.close();
            }
            ConnectionPoolManager.releaseConnection(connection);
            return carts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer save(Cart cart) throws SQLException {
        String sql = "INSERT INTO `carts` (`user_id`, `product_id`, `product_quantity`, `cart_health`) VALUES (?, ?, ?, ?)";
        return CRUDUtils.save(sql, cart.getUserId(), cart.getProductId(), cart.getProductQuantity(), cart.getCartHealth());
    }

    @Override
    public void update(Cart cart) throws SQLException {
        String sql = "UPDATE `carts` SET `product_quantity` = ?, `cart_health` = ? WHERE `cart_id` = ?";
        CRUDUtils.update(sql, cart.getProductQuantity(), cart.getCartHealth(), cart.getCartId());
    }

    @Override
    public void delete(int cartId) throws SQLException {
        String sql = "DELETE FROM `carts` WHERE `cart_id` = ?";
        CRUDUtils.update(sql, cartId);
    }
}

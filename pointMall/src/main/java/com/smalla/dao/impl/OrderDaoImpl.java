package com.smalla.dao.impl;

import com.smalla.dao.OrderDao;
import com.smalla.po.Order;
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
public class OrderDaoImpl implements OrderDao {

    private static volatile OrderDaoImpl instance;

    public static OrderDaoImpl getInstance() {
        if (instance == null) {
            synchronized (OrderDaoImpl.class) {
                if (instance == null) {
                    instance = new OrderDaoImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public List<Order> findByActiveId(int activeId) {
        String sql = "SELECT `order_id`, `active_id`, `passive_id`, `product_id`, `product_number`, `product_unit_price`, `product_total_price`, `description`, `order_status` FROM `orders` WHERE `active_id` = ?";
        Connection connection = ConnectionPoolManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet rs = CRUDUtils.query(sql, connection, preparedStatement, activeId);
        List<Order> orders = new ArrayList<>();

        try {
            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("order_id"));
                order.setActiveId(rs.getInt("active_id"));
                order.setPassiveId(rs.getInt("passive_id"));
                order.setProductId(rs.getInt("product_id"));
                order.setProductNumber(rs.getInt("product_number"));
                order.setProductUnitPrice(rs.getString("product_unit_price"));
                order.setProductTotalPrice(rs.getString("product_total_price"));
                order.setDescription(rs.getString("description"));
                order.setOrderStatus(rs.getString("order_status"));
                orders.add(order);
            }
            if(rs != null) {
                rs.close();
            }
            if(preparedStatement != null) {
                preparedStatement.close();
            }
            ConnectionPoolManager.releaseConnection(connection);
            return orders;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer save(Order order) throws SQLException {
        String sql = "INSERT INTO `orders` (`active_id`, `passive_id`, `product_id`, `product_number`, `product_unit_price`, `product_total_price`, `description`, `order_status`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return CRUDUtils.save(sql, order.getActiveId(), order.getPassiveId(), order.getProductId(), order.getProductNumber(), order.getProductUnitPrice(), order.getProductTotalPrice(), order.getDescription(), order.getOrderStatus());
    }

    @Override
    public void update(Order order) throws SQLException {
        String sql = "UPDATE `orders` SET `passive_id` = ?, `product_id` = ?, `product_number` = ?, `product_unit_price` = ?, `product_total_price` = ?, `description` = ?, `order_status` = ? WHERE `order_id` = ?";
        CRUDUtils.update(sql, order.getPassiveId(), order.getProductId(), order.getProductNumber(), order.getProductUnitPrice(), order.getProductTotalPrice(), order.getDescription(), order.getOrderStatus(), order.getOrderId());
    }

    @Override
    public void delete(int orderId) throws SQLException {
        String sql = "DELETE FROM `orders` WHERE `order_id` = ?";
        CRUDUtils.update(sql, orderId);
    }
}

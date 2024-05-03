package com.smalla.dao;

import com.smalla.po.Order;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Sardh
 */
public interface OrderDao {
    /**
     * 根据发起付款者ID来查找
     * @param activeId
     * @return
     */
    List<Order> listByActiveId(int activeId);

    /**
     * 根据发起订单ID来查找
     * @param orderId
     * @return
     */
    List<Order> listByOrderId(int orderId);

    /**
     * 根据发起订单ID来查找
     * @param merchantId
     * @return
     */
    List<Order> listByPassiveIdAndOrderStatus(int merchantId, String orderStatus);

    /**
     * 根据发起订单ID来查找
     * @param orderId
     * @return
     */
    Order getByOrderId(int orderId);

    /**
     * 根据付款者ID和订单状态来查找
     * @param userId
     * @param orderStatus
     * @return
     */
    List<Order> listByActiveIdAndCartHealth(int userId, String orderStatus);

    /**
     * 保存
     * @param order
     * @return
     * @throws SQLException
     */
    Integer save(Order order) throws SQLException;

    /**
     * 更新一个条目
     * @param order
     * @throws SQLException
     */
    void update(Order order) throws SQLException;

    /**
     * 删除一个条目
     * @param orderId
     * @throws SQLException
     */
    void delete(int orderId) throws SQLException;
}

package com.smalla.dao;

import com.smalla.dao.impl.OrderDaoImpl;
import com.smalla.po.Order;

import java.sql.SQLException;
import java.util.List;

public class TestOrderDao {

    public static void main(String[] args) {
        OrderDao orderDao = OrderDaoImpl.getInstance();

        // 测试保存订单
        Order newOrder = new Order();
        newOrder.setActiveId(1);
        newOrder.setPassiveId(2);
        newOrder.setProductId(1);
        newOrder.setProductNumber(3);
        newOrder.setProductUnitPrice("200.00");
        newOrder.setProductTotalPrice("600.00");
        newOrder.setDescription("Test Order Description");
        newOrder.setOrderStatus("Pending");
        try {
            Integer orderId = orderDao.save(newOrder);
            System.out.println("新订单的 ID：" + orderId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 测试根据激活 ID 查找订单
        int activeIdToFind = 1; // 修改为你要查找的激活 ID
        List<Order> foundOrders = orderDao.findByActiveId(activeIdToFind);
        if (!foundOrders.isEmpty()) {
            Order foundOrder = foundOrders.get(0);
            System.out.println("找到的订单信息：" + foundOrder);
        } else {
            System.out.println("未找到激活 ID 为 " + activeIdToFind + " 的订单");
        }

        // 测试更新订单信息
        int orderIdToUpdate = 1; // 修改为你要更新的订单 ID
        List<Order> ordersToUpdate = orderDao.findByActiveId(activeIdToFind);
        if (!ordersToUpdate.isEmpty()) {
            Order orderToUpdate = ordersToUpdate.get(0);
            orderToUpdate.setProductNumber(5);
            orderToUpdate.setProductUnitPrice("220.00");
            orderToUpdate.setProductTotalPrice("1100.00");
            orderToUpdate.setDescription("Updated Order Description");
            orderToUpdate.setOrderStatus("Processed");
            try {
                orderDao.update(orderToUpdate);
                System.out.println("订单信息已更新");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("未找到订单 ID 为 " + orderIdToUpdate + " 的订单，无法更新");
        }

        // 测试删除订单
        int orderIdToDelete = 1; // 修改为你要删除的订单 ID
        try {
            orderDao.delete(orderIdToDelete);
            System.out.println("订单删除成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

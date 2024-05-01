package com.smalla.dao;

import com.smalla.dao.impl.CartDaoImpl;
import com.smalla.po.Cart;

import java.sql.SQLException;
import java.util.List;

public class TestCartDao {

    public static void main(String[] args) {
        CartDao cartDao = CartDaoImpl.getInstance();

        // 测试保存购物车项
        Cart newCart = new Cart();
        newCart.setUserId(1);
        newCart.setProductId(1);
        newCart.setProductQuantity(10);
        newCart.setCartHealth(1);
        try {
            Integer cartId = cartDao.save(newCart);
            System.out.println("新购物车项的 ID：" + cartId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 测试根据用户 ID 查找购物车项
        int userIdToFind = 1; // 修改为你要查找的用户 ID
        List<Cart> foundCarts = cartDao.findByUserId(userIdToFind);
        if (!foundCarts.isEmpty()) {
            Cart foundCart = foundCarts.get(0);
            System.out.println("找到的购物车项信息：" + foundCart);
        } else {
            System.out.println("未找到用户 ID 为 " + userIdToFind + " 的购物车项");
        }

        // 测试更新购物车项信息
        int cartIdToUpdate = 1; // 修改为你要更新的购物车项 ID
        List<Cart> cartsToUpdate = cartDao.findByUserId(userIdToFind);
        if (!cartsToUpdate.isEmpty()) {
            Cart cartToUpdate = cartsToUpdate.get(0);
            cartToUpdate.setProductQuantity(20);
            cartToUpdate.setCartHealth(2);
            try {
                cartDao.update(cartToUpdate);
                System.out.println("购物车项信息已更新");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("未找到购物车项 ID 为 " + cartIdToUpdate + " 的购物车项，无法更新");
        }

        // 测试删除购物车项
        int cartIdToDelete = 1; // 修改为你要删除的购物车项 ID
        try {
            cartDao.delete(cartIdToDelete);
            System.out.println("购物车项删除成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

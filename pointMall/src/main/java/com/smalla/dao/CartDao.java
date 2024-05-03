package com.smalla.dao;

import com.smalla.po.Cart;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Sardh
 */
public interface CartDao {
    /**
     * 根据用户ID来查找
     * @param userId
     * @return
     */
    List<Cart> listByUserId(int userId);

    /**
     * 根据用户ID和购物车是否可用来查找
     * @param userId
     * @param cartHealth
     * @return
     */
    List<Cart> listByUserIdAndCartHealth(int userId, int cartHealth);

    /**
     * 根据购物车ID来查找
     * @param cartId
     * @return
     */
    List<Cart> listByCartId(int cartId);

    /**
     * 根据购物车ID来查找
     * @param cartId
     * @return
     */
    Cart getByCartId(int cartId);

    /**
     * 保存
     * @param cart
     * @return
     * @throws SQLException
     */
    Integer save(Cart cart) throws SQLException;

    /**
     * 更新一个条目
     * @param cart
     * @throws SQLException
     */
    void update(Cart cart) throws SQLException;

    /**
     * 删除一个条目
     * @param cartId
     * @throws SQLException
     */
    void delete(int cartId) throws SQLException;
}

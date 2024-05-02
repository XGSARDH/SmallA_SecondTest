package com.smalla.dao;

import com.smalla.po.User;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Sardh
 */
public interface UserDao {
    /**
     * 根据用户ID来查找
     * @param userId
     * @return
     */
    List<User> listById(int userId);

    /**
     * 根据用户ID来查找单个用户
     * @param userId
     * @return
     */
    User getById(int userId);

    /**
     * 根据手机号码来查找
     * @param phone
     * @return
     */
    List<User> listByPhone(String phone);

    /**
     * 根据手机号码来查找单个用户
     * @param phone
     * @return
     */
    User getByPhone(String phone);

    /**
     * 查找全部
     * @return
     */
    List<User> listAll();

    /**
     * 保存
     * @param user
     * @return
     * @throws SQLException
     */
    Integer save(User user) throws SQLException;

    /**
     * 更新一个条目
     * @param user
     * @throws SQLException
     */
    void update(User user) throws SQLException;

    /**
     * 删除一个条目
     * @param userId
     * @throws SQLException
     */
    void delete(int userId) throws SQLException;
}

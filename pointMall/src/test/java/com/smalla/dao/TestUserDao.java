package com.smalla.dao;

import com.smalla.dao.impl.UserDaoImpl;
import com.smalla.factory.DaoFactory;
import com.smalla.po.User;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class TestUserDao {

    public static void main(String[] args) {
        UserDao userDao = UserDaoImpl.getInstance();

        // 测试保存用户
        User newUser = new User();
        newUser.setFundId(1);
        newUser.setUsername("testUser");
        newUser.setPassword("testPassword");
        newUser.setPhone("123456789");
        newUser.setUserHealth(1);
        newUser.setIsMerchant(0);
        newUser.setDefaultAddress("GDUT");
        newUser.setLastCheckInDate(Date.valueOf("2024-05-02"));
        Integer userId = 1;
        try {
            userId = userDao.save(newUser);
            System.out.println("新用户的 ID：" + userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 测试根据用户 ID 查找用户
        int userIdTolist = userId; // 修改为你要查找的用户 ID
        List<User> foundUsers = userDao.listById(userIdTolist);
        if (!foundUsers.isEmpty()) {
            User foundUser = foundUsers.get(0);
            System.out.println("找到的用户信息：" + foundUser);
        } else {
            System.out.println("未找到 ID 为 " + userIdTolist + " 的用户");
        }
        System.out.println(userIdTolist);

        // 测试更新用户信息
        int userIdToUpdate = userId; // 修改为你要更新的用户 ID
        List<User> usersToUpdate = userDao.listById(userIdToUpdate);
        if (!usersToUpdate.isEmpty()) {
            User userToUpdate = usersToUpdate.get(0);
            userToUpdate.setUsername("updatedUsername");
            userToUpdate.setPassword("updatedPassword");
            userToUpdate.setPhone("987654321");
            try {
                userDao.update(userToUpdate);
                System.out.println("用户信息已更新");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("未找到 ID 为 " + userIdToUpdate + " 的用户，无法更新");
        }

        System.out.println(DaoFactory.getUserDao().getById(3));
        System.out.println(DaoFactory.getUserDao().getByPhone("0"));

        // 测试删除用户
//        int userIdToDelete = 1; // 修改为你要删除的用户 ID
//        try {
//            userDao.delete(userIdToDelete);
//            System.out.println("用户删除成功");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
}

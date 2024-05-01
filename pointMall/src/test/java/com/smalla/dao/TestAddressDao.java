package com.smalla.dao;

import com.smalla.dao.impl.AddressDaoImpl;
import com.smalla.po.Address;

import java.sql.SQLException;
import java.util.List;

public class TestAddressDao {

    public static void main(String[] args) {
        AddressDao addressDao = AddressDaoImpl.getInstance();

        // 测试保存地址
        Address newAddress = new Address();
        newAddress.setUserId(1);
        newAddress.setPhone("1234567890");
        newAddress.setAddress("1234 Main St, Anytown, USA");
        try {
            Integer addressId = addressDao.save(newAddress);
            System.out.println("新地址的 ID：" + addressId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 测试根据用户 ID 查找地址
        int userIdToFind = 1; // 修改为你要查找的用户 ID
        List<Address> foundAddresses = addressDao.findByUserId(userIdToFind);
        if (!foundAddresses.isEmpty()) {
            Address foundAddress = foundAddresses.get(0);
            System.out.println("找到的地址信息：" + foundAddress);
        } else {
            System.out.println("未找到用户 ID 为 " + userIdToFind + " 的地址");
        }

        // 测试更新地址信息
        int addressIdToUpdate = 1; // 修改为你要更新的地址 ID
        List<Address> addressesToUpdate = addressDao.findByUserId(userIdToFind);
        if (!addressesToUpdate.isEmpty()) {
            Address addressToUpdate = addressesToUpdate.get(0);
            addressToUpdate.setPhone("0987654321");
            addressToUpdate.setAddress("4321 Side St, Anothertown, USA");
            try {
                addressDao.update(addressToUpdate);
                System.out.println("地址信息已更新");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("未找到地址 ID 为 " + addressIdToUpdate + " 的地址，无法更新");
        }

        // 测试删除地址
        int addressIdToDelete = 1; // 修改为你要删除的地址 ID
        try {
            addressDao.delete(addressIdToDelete);
            System.out.println("地址删除成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package com.smalla.dao;

import com.smalla.dao.impl.MerchantDaoImpl;
import com.smalla.po.Merchant;

import java.sql.SQLException;
import java.util.List;

public class TestMerchantDao {

    public static void main(String[] args) {
        MerchantDao merchantDao = MerchantDaoImpl.getInstance();

        // 测试保存商户
        Merchant newMerchant = new Merchant();
        newMerchant.setUserId(1);
        newMerchant.setFundId(1);
        newMerchant.setMerchantName("TestMerchant");
        newMerchant.setMerchantHealth(1);
        try {
            Integer merchantId = merchantDao.save(newMerchant);
            System.out.println("新商户的 ID：" + merchantId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 测试根据用户 ID 查找商户
        int userIdTolist = 1; // 修改为你要查找的用户 ID
        List<Merchant> foundMerchants = merchantDao.listByUserId(userIdTolist);
        if (!foundMerchants.isEmpty()) {
            Merchant foundMerchant = foundMerchants.get(0);
            System.out.println("找到的商户信息：" + foundMerchant);
        } else {
            System.out.println("未找到用户 ID 为 " + userIdTolist + " 的商户");
        }

        // 测试更新商户信息
        int merchantIdToUpdate = 1; // 修改为你要更新的商户 ID
        List<Merchant> merchantsToUpdate = merchantDao.listByUserId(userIdTolist);
        if (!merchantsToUpdate.isEmpty()) {
            Merchant merchantToUpdate = merchantsToUpdate.get(0);
            merchantToUpdate.setMerchantName("UpdatedMerchant");
            merchantToUpdate.setMerchantHealth(2);
            try {
                merchantDao.update(merchantToUpdate);
                System.out.println("商户信息已更新");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("未找到商户 ID 为 " + merchantIdToUpdate + " 的商户，无法更新");
        }

        // 测试删除商户
        int merchantIdToDelete = 1; // 修改为你要删除的商户 ID
        try {
            merchantDao.delete(merchantIdToDelete);
            System.out.println("商户删除成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

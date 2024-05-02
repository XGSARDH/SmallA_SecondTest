package com.smalla.dao;

import com.smalla.dao.impl.FundDaoImpl;
import com.smalla.po.Fund;

import java.sql.SQLException;
import java.util.List;

public class TestFundDao {

    public static void main(String[] args) {
        FundDao fundDao = FundDaoImpl.getInstance();

        // 测试保存资金
        Fund newFund = new Fund();
        newFund.setUserId(1);
        newFund.setTotalFunds("10000.00");
        newFund.setAvailableFunds("8000.00");
        newFund.setFrozenFunds("2000.00");
        newFund.setFundHealth(1);
        try {
            Integer fundId = fundDao.save(newFund);
            System.out.println("新资金的 ID：" + fundId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 测试根据用户 ID 查找资金
        int userIdTolist = 1; // 修改为你要查找的用户 ID
        List<Fund> foundFunds = fundDao.listByUserId(userIdTolist);
        if (!foundFunds.isEmpty()) {
            Fund foundFund = foundFunds.get(0);
            System.out.println("找到的资金信息：" + foundFund);
        } else {
            System.out.println("未找到用户 ID 为 " + userIdTolist + " 的资金");
        }

        // 测试更新资金信息
        int fundIdToUpdate = 1; // 修改为你要更新的资金 ID
        List<Fund> fundsToUpdate = fundDao.listByUserId(userIdTolist);
        if (!fundsToUpdate.isEmpty()) {
            Fund fundToUpdate = fundsToUpdate.get(0);
            fundToUpdate.setTotalFunds("12000.00");
            fundToUpdate.setAvailableFunds("9000.00");
            fundToUpdate.setFrozenFunds("3000.00");
            fundToUpdate.setFundHealth(2);
            try {
                fundDao.update(fundToUpdate);
                System.out.println("资金信息已更新");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("未找到资金 ID 为 " + fundIdToUpdate + " 的资金，无法更新");
        }

        // 测试删除资金
        int fundIdToDelete = 1; // 修改为你要删除的资金 ID
        try {
            fundDao.delete(fundIdToDelete);
            System.out.println("资金删除成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package com.smalla.dao;

import com.smalla.dao.FundFlowDao;
import com.smalla.dao.impl.FundFlowDaoImpl;
import com.smalla.po.FundFlow;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class TestFundFlowDao {

    public static void main(String[] args) {
        FundFlowDao fundFlowDao = FundFlowDaoImpl.getInstance();

        // 测试保存资金流
        FundFlow newFundFlow = new FundFlow();
        newFundFlow.setOrderId(1);
        newFundFlow.setActiveId(1001);
        newFundFlow.setPassiveId(2001);
        newFundFlow.setAmount(new BigDecimal("1000.00"));
        newFundFlow.setChangeType("Payment");
        newFundFlow.setFundflowStatus("Completed");
        try {
            Integer fundFlowId = fundFlowDao.save(newFundFlow);
            System.out.println("新资金流的 ID：" + fundFlowId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 测试查找资金流
        int orderIdTolist = 1; // 修改为你要查找的订单 ID
        List<FundFlow> foundFundFlows = fundFlowDao.listByOrderId(orderIdTolist);
        if (!foundFundFlows.isEmpty()) {
            System.out.println("找到的资金流信息：");
            for (FundFlow fundFlow : foundFundFlows) {
                System.out.println(fundFlow);
            }
        } else {
            System.out.println("未找到订单 ID 为 " + orderIdTolist + " 的资金流");
        }

        // 测试更新资金流信息
        int fundFlowIdToUpdate = 1; // 修改为你要更新的资金流 ID
        List<FundFlow> fundFlowsToUpdate = fundFlowDao.listByOrderId(orderIdTolist);
        if (!fundFlowsToUpdate.isEmpty()) {
            FundFlow fundFlowToUpdate = fundFlowsToUpdate.get(0);
            fundFlowToUpdate.setAmount(new BigDecimal("1200.00"));
            fundFlowToUpdate.setFundflowStatus("Pending");
            try {
                fundFlowDao.update(fundFlowToUpdate);
                System.out.println("资金流信息已更新");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("未找到资金流 ID 为 " + fundFlowIdToUpdate + " 的资金流，无法更新");
        }

        // 测试删除资金流
        int fundFlowIdToDelete = 1; // 修改为你要删除的资金流 ID
        try {
            fundFlowDao.delete(fundFlowIdToDelete);
            System.out.println("资金流删除成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

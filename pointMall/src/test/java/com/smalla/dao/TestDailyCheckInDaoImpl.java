package com.smalla.dao;

import com.smalla.dao.impl.DailyCheckInDaoImpl;
import com.smalla.po.DailyCheckIn;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class TestDailyCheckInDaoImpl {

    public static void main(String[] args) {
        // 创建DailyCheckInDaoImpl对象
        DailyCheckInDaoImpl dailyCheckInDao = DailyCheckInDaoImpl.getInstance();

        // 测试getAllCheckIns()方法
        System.out.println("Testing getAllCheckIns()...");
        List<DailyCheckIn> checkIns = dailyCheckInDao.getAllCheckIns();
        for (DailyCheckIn checkIn : checkIns) {
            System.out.println(checkIn);
        }
        System.out.println();

        // 测试saveCheckIn()方法
        System.out.println("Testing saveCheckIn()...");
        DailyCheckIn newCheckIn = new DailyCheckIn();
        newCheckIn.setCheckInDate(new Date());
        newCheckIn.setNumberOfPeople(10);
        try {
            dailyCheckInDao.saveCheckIn(newCheckIn);
            System.out.println("New check-in saved successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to save new check-in: " + e.getMessage());
        }
        System.out.println();

        // 测试findCheckInByDate()方法
        System.out.println("Testing findCheckInByDate()...");
        Date searchDate = new Date(); // 使用当前日期进行测试
        DailyCheckIn foundCheckIn = dailyCheckInDao.findCheckInByDate(searchDate);
        if (foundCheckIn != null) {
            System.out.println("Check-in found: " + foundCheckIn);
        } else {
            System.out.println("No check-in found for date: " + searchDate);
        }
        System.out.println();

        // 测试updateCheckInByDate()方法
        System.out.println("Testing updateCheckInByDate()...");
        int updatedNumberOfPeople = 15; // 假设更新为15人
        try {
            dailyCheckInDao.updateCheckInByDate(searchDate, updatedNumberOfPeople);
            System.out.println("Check-in updated successfully for date: " + searchDate);
        } catch (SQLException e) {
            System.out.println("Failed to update check-in: " + e.getMessage());
        }
        System.out.println();

        // 测试deleteCheckIn()方法（可选）
        /*
        System.out.println("Testing deleteCheckIn()...");
        int checkInIdToDelete = 1; // 假设要删除的check-in的ID为1
        try {
            dailyCheckInDao.deleteCheckIn(checkInIdToDelete);
            System.out.println("Check-in deleted successfully with ID: " + checkInIdToDelete);
        } catch (SQLException e) {
            System.out.println("Failed to delete check-in with ID " + checkInIdToDelete + ": " + e.getMessage());
        }
        */
    }
}

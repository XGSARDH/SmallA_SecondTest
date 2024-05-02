package com.smalla.dao;

import com.smalla.po.DailyCheckIn;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface DailyCheckInDao {
    /**
     * 查找全都签到记录
     * @return
     */
    List<DailyCheckIn> getAllCheckIns();

    /**
     * 保存签到记录
     * @param checkIn
     * @throws SQLException
     */
    void saveCheckIn(DailyCheckIn checkIn) throws SQLException;

    /**
     * 删除签到记录
     * @param checkInId
     * @throws SQLException
     */
    void deleteCheckIn(int checkInId) throws SQLException;

    /**
     * 根据日期查找签到记录
     * @param checkInDate
     * @return
     */
    DailyCheckIn findCheckInByDate(Date checkInDate);

    /**
     * 根据日期更新签到记录
     * @param checkInDate
     * @param numberOfPeople
     * @throws SQLException
     */
    void updateCheckInByDate(Date checkInDate, int numberOfPeople) throws SQLException;
}

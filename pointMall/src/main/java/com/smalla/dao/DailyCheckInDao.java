package com.smalla.dao;

import com.smalla.po.DailyCheckIn;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface DailyCheckInDao {
    List<DailyCheckIn> getAllCheckIns();

    void saveCheckIn(DailyCheckIn checkIn) throws SQLException;

    void deleteCheckIn(int checkInId) throws SQLException;

    DailyCheckIn findCheckInByDate(Date checkInDate);

    void updateCheckInByDate(Date checkInDate, int numberOfPeople) throws SQLException;
}

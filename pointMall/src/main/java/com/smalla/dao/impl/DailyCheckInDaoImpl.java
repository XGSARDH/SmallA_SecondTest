package com.smalla.dao.impl;

import com.smalla.dao.DailyCheckInDao;
import com.smalla.po.DailyCheckIn;
import com.smalla.util.connectPool.ConnectionPoolManager;
import com.smalla.util.crudUtils.CRUDUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DailyCheckInDaoImpl implements DailyCheckInDao {

    private static volatile DailyCheckInDaoImpl instance;

    public static DailyCheckInDaoImpl getInstance() {
        if (instance == null) {
            synchronized (DailyCheckInDaoImpl.class) {
                if (instance == null) {
                    instance = new DailyCheckInDaoImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public List<DailyCheckIn> getAllCheckIns() {
        String sql = "SELECT * FROM `dailycheckin`";
        Connection connection = ConnectionPoolManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet rs = CRUDUtils.query(sql, connection, preparedStatement);
        List<DailyCheckIn> checkIns = new ArrayList<>();

        try {
            while (rs.next()) {
                DailyCheckIn checkIn = new DailyCheckIn();
                checkIn.setId(rs.getInt("id"));
                checkIn.setCheckInDate(rs.getDate("check_in_date"));
                checkIn.setNumberOfPeople(rs.getInt("number_of_people"));
                checkIns.add(checkIn);
            }
            if(rs != null) {
                rs.close();
            }
            if(preparedStatement != null) {
                preparedStatement.close();
            }
            ConnectionPoolManager.releaseConnection(connection);
            return checkIns;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveCheckIn(DailyCheckIn checkIn) throws SQLException {
        String sql = "INSERT INTO `dailycheckin` (`check_in_date`, `number_of_people`) VALUES (?, ?)";
        CRUDUtils.save(sql, checkIn.getCheckInDate(), checkIn.getNumberOfPeople());
    }

    @Override
    public void deleteCheckIn(int checkInId) throws SQLException {
        String sql = "DELETE FROM `dailycheckin` WHERE `id` = ?";
        CRUDUtils.update(sql, checkInId);
    }

    @Override
    public DailyCheckIn listCheckInByDate(Date checkInDate) {
        String sql = "SELECT * FROM `dailycheckin` WHERE `check_in_date` = ?";
        Connection connection = ConnectionPoolManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1, new java.sql.Date(checkInDate.getTime()));
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                DailyCheckIn checkIn = new DailyCheckIn();
                checkIn.setId(rs.getInt("id"));
                checkIn.setCheckInDate(rs.getDate("check_in_date"));
                checkIn.setNumberOfPeople(rs.getInt("number_of_people"));
                ConnectionPoolManager.releaseConnection(connection);
                return checkIn;
            }
            ConnectionPoolManager.releaseConnection(connection);
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateCheckInByDate(Date checkInDate, int numberOfPeople) throws SQLException {
        String sql = "UPDATE `dailycheckin` SET `number_of_people` = ? WHERE `check_in_date` = ?";
        CRUDUtils.update(sql, numberOfPeople, new java.sql.Date(checkInDate.getTime()));
    }
}

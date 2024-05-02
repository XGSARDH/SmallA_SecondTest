package com.smalla.po;

import java.util.Date;

public class DailyCheckIn {
    private int id;
    private Date checkInDate;
    private int numberOfPeople;

    public DailyCheckIn() {
    }

    public DailyCheckIn(Date checkInDate, int numberOfPeople) {
        this.checkInDate = checkInDate;
        this.numberOfPeople = numberOfPeople;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    @Override
    public String toString() {
        return "DailyCheckIn{" +
                "id=" + id +
                ", checkInDate=" + checkInDate +
                ", numberOfPeople=" + numberOfPeople +
                '}';
    }
}

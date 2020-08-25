package cn.xports.entity;

import java.io.Serializable;

public class BookingDay implements Serializable {
    private String date;
    private String day;
    private String week;

    public String getDate() {
        return this.date;
    }

    public BookingDay setDate(String str) {
        this.date = str;
        return this;
    }

    public String getWeek() {
        return this.week;
    }

    public BookingDay setWeek(String str) {
        this.week = str;
        return this;
    }

    public String getDay() {
        return this.day;
    }

    public BookingDay setDay(String str) {
        this.day = str;
        return this;
    }
}

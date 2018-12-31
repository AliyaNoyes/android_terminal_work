package com.terminal_work.android.terminal_work;

import java.util.Objects;

public class DATE {
    private int year;
    private int month;
    private int day;
    public DATE(int year,int month,int day){
        this.year=year;
        this.month=month;
        this.day=day;
    }
    public DATE(){}
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DATE date = (DATE) o;
        return year == date.year &&
                month == date.month &&
                day == date.day;
    }

    @Override
    public int hashCode() {

        return Objects.hash(year, month, day);
    }
}

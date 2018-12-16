package com.terminal_work.android.terminal_work;

import java.util.Calendar;
import java.util.List;

public class daily_bill {

    private Calendar date;
    private List<trading_project> bill;

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public List<trading_project> getBill() {
        return bill;
    }

    public void setBill(List<trading_project> bill) {
        this.bill = bill;
    }
}

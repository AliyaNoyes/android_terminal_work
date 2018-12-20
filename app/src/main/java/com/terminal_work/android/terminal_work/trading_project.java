package com.terminal_work.android.terminal_work;

import java.io.Serializable;
import java.util.Calendar;

public class trading_project implements Serializable {

    //分配UUID
    private String name;
    private int type;//1--incoming,-1--outgoing
    private int amount;//1--credit,0--crash
    private Calendar time;
    private int mode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Calendar getTime() {
        return time;
    }

    public void setTime(Calendar time) {
        this.time = time;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }
}

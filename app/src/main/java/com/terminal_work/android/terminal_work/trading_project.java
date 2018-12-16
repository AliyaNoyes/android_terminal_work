package com.terminal_work.android.terminal_work;

import java.util.Calendar;

public class trading_project {

    private final int INCOMING=1;
    private final int OUTGOING=-1;
    private final int CRASH=0;
    private final int CREDIT=1;
    private String name;
    private int type;
    private int amount;
    private Calendar time;
    private int mode;

    public int getINCOMING() {
        return INCOMING;
    }

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

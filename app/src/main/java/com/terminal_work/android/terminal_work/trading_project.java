package com.terminal_work.android.terminal_work;

import java.io.Serializable;
import java.util.Calendar;
import java.util.UUID;

public class trading_project implements Serializable {

    //分配UUID
    private UUID id;
    private String name;
    private int type;//1--incoming,-1--outgoing
    private double amount;
    private Calendar time;
    private int mode;//1--credit,-1--crash

    public trading_project(){
        id =UUID.randomUUID();
    }
    public trading_project(UUID id){
        this.id=id;
    }

    public UUID getId() {
        return id;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
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

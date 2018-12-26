package com.terminal_work.android.terminal_work;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

public class daily_bill implements Serializable {

    private Calendar date;
    private List<trading_project> bills;
    private int sumAmount;

    public daily_bill(Calendar d){
        date=d;
        bills =new ArrayList<>();
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public List<trading_project> getBills() {
        return bills;
    }

    public void setBills(List<trading_project> bills) {
        this.bills = bills;
    }

    public int getSumAmount() {
        sumAmount=0;
        for(int i = 0; i< bills.size(); i++) {
            if(bills.get(i).getType()==1){
                sumAmount=sumAmount+ bills.get(i).getAmount();
            }
            else
                sumAmount=sumAmount- bills.get(i).getAmount();
        }
        return sumAmount;
    }

    public trading_project getProject(UUID id){
        for(int i=0;i<bills.size();i++){
            if (bills.get(i).getId()==id)
                return bills.get(i);
        }
        return null;
    }
}

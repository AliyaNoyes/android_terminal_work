package com.terminal_work.android.terminal_work;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

public class daily_bill implements Serializable {

    private Calendar date;
    private List<trading_project> bills;
    private double sumAmount;

    public daily_bill(Calendar d){
        date=d;
        bills =new ArrayList<>();
    }
    public daily_bill(){
        bills=new ArrayList<>();
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

    public double getSumAmount() {
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

    @Override
    public boolean equals(Object obj) {
        if(obj==null){
            return false;
        }
        else{
            if(obj instanceof daily_bill){
                daily_bill db=(daily_bill)obj;
                if((date.get(Calendar.YEAR)==(db.getDate().get(Calendar.YEAR)))&&
                        (date.get(Calendar.MONTH)==(db.getDate().get(Calendar.MONTH)))&&
                        (date.get(Calendar.DAY_OF_MONTH)==(db.getDate().get(Calendar.DAY_OF_MONTH))))
                    return true;
            }
        }
        return false;
    }
}

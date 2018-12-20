package com.terminal_work.android.terminal_work;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class daily_bill {

    private Calendar date;
    private List<trading_project> bill;
    private int sumAmount;

    public daily_bill(Calendar d){
        date=d;
        bill=new ArrayList<>();
    }

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

    public int getSumAmount() {
        sumAmount=0;
        for(int i=0;i<bill.size();i++) {
            if(bill.get(i).getType()==1){
                sumAmount=sumAmount+bill.get(i).getAmount();
            }
            else
                sumAmount=sumAmount-bill.get(i).getAmount();
        }
        return sumAmount;
    }
}

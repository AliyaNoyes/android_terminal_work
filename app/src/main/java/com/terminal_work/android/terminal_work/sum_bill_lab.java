package com.terminal_work.android.terminal_work;

import android.content.Context;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class sum_bill_lab {

    private static sum_bill_lab sLab;
    private List<daily_bill> mDailyBills;

    public static sum_bill_lab get(Context context){
        if(sLab==null){
            sLab=new sum_bill_lab(context);
        }
        return sLab;
    }
    private sum_bill_lab(Context context){
        mDailyBills=new ArrayList<>();
    }
    public List<daily_bill> getDailyBills(){
        return mDailyBills;
    }
    public daily_bill getDailyBill(Calendar calendar){
        for(daily_bill mbill:mDailyBills){
            if(mbill.getDate().equals(calendar)){
                return mbill;
            }
        }
        return null;
    }
}

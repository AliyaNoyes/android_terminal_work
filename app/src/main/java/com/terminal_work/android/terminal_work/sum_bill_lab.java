package com.terminal_work.android.terminal_work;

import android.content.Context;

public class sum_bill_lab {
    private static sum_bill_lab sLab;
    public static sum_bill_lab get(Context context){
        if(sLab==null){
            sLab=new sum_bill_lab(context);
        }
        return sLab;
    }
    private sum_bill_lab(Context context){

    }
}

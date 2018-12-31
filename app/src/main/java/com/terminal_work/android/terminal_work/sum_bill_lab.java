package com.terminal_work.android.terminal_work;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public class sum_bill_lab {

    private static sum_bill_lab sLab;
    private List<daily_bill> mDailyBills;
    private Context mContext;
    private SQLiteDatabase mDatabase;


    public static sum_bill_lab get(Context context){
        if(sLab==null){
            sLab=new sum_bill_lab(context);
        }
        return sLab;
    }
    private sum_bill_lab(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new BillBaseHelper(mContext).getWritableDatabase();
        getdatafromdb();
    }

    private static ContentValues getContentValues(trading_project mProject){
        ContentValues values = new ContentValues();
        Calendar time=mProject.getTime();
        values.put("username", LoadActivity.Username);
        values.put("uuidString", mProject.getId().toString());
        values.put("name",  mProject.getName());
        values.put("amount",mProject.getAmount());
        values.put("mode",mProject.getMode());
        values.put("type",mProject.getType());
        values.put("year",time.get(Calendar.YEAR));
        values.put("month",time.get(Calendar.MONTH));
        values.put("day",time.get(Calendar.DAY_OF_MONTH));
        values.put("hour",time.get(Calendar.HOUR_OF_DAY));
        values.put("minute",time.get(Calendar.MINUTE));

        return values;
    }

    public void getdatafromdb(){
        mDailyBills=new ArrayList<>();
        List<DATE> mDate=new ArrayList<>();
        /*List<Calendar> mDtae=new ArrayList<>();*/
        List<trading_project> mtrading_project=new ArrayList<>();

        String SQL="create table IF NOT EXISTS bill(username varchar(20),uuidString varchar(20),name varchar(20),amount double,mode integer,type integer,year integer,month integer,day integer,hour integer,minute integer)";
        mDatabase.execSQL(SQL);
        String sql="select * from bill where username=?";
        Cursor cursor=mDatabase.rawQuery(sql,new String[]{LoadActivity.Username});
        if(cursor!=null&&cursor.moveToFirst()) {
            do{
                Calendar time = Calendar.getInstance();
                /*Calendar date=Calendar.getInstance();*/

                String uuidString = cursor.getString(1);
                String name = cursor.getString(2);
                double amount = cursor.getDouble(3);
                int mode = cursor.getInt(4);
                int type = cursor.getInt(5);
                int year = cursor.getInt(6);
                int month = cursor.getInt(7);
                int day = cursor.getInt(8);
                int hour = cursor.getInt(9);
                int minute = cursor.getInt(10);
                DATE date=new DATE(year,month,day);
                /*date.set(year,month,day,hour,minute,0);
                mDate.add(date);*/
                mDate.add(date);
                time.set(year, month, day, hour, minute,0);
                trading_project mProject = new trading_project(UUID.fromString(uuidString));
                mProject.setName(name);
                mProject.setAmount(amount);
                mProject.setMode(mode);
                mProject.setType(type);
                mProject.setTime(time);
                mtrading_project.add(mProject);
            }while (cursor.moveToNext());
        }
        cursor.close();
             /*HashSet<Calendar> h = new HashSet<Calendar>(mDate);
             mDate.clear();
             mDate.addAll(h);*/
        HashSet<DATE> h = new HashSet<DATE>(mDate);
        mDate.clear();
        mDate.addAll(h);
        for (int i = 0; i < mDate.size(); i++) {
            /*Calendar date=mDate.get(i);*/
            DATE date=mDate.get(i);
            daily_bill mDaily_bill = new daily_bill();
            for (int j = 0; j < mtrading_project.size(); j++) {
                Calendar time = mtrading_project.get(j).getTime();
                if (date.getYear() == time.get(Calendar.YEAR) && date.getMonth() == time.get(Calendar.MONTH) && date.getDay()== time.get(Calendar.DAY_OF_MONTH)) {
                    mDaily_bill.setDate(time);
                    mDaily_bill.getBills().add(mtrading_project.get(j));
                }
            }
            mDailyBills.add(mDaily_bill);
        }

    }
    public List<daily_bill> getDailyBills(){
        return mDailyBills;
    }

    public void insertbill(trading_project mProject){
        ContentValues values=getContentValues(mProject);
        mDatabase.insert("bill",null,values);
    }

    public void updatebill(trading_project mProject){
        ContentValues values=getContentValues(mProject);
        mDatabase.update("bill",values,"uuidString=?",new String[]{mProject.getId().toString()});

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

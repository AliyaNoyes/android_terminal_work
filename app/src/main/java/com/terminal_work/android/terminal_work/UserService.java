package com.terminal_work.android.terminal_work;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class UserService {
    private BillBaseHelper mBillBaseHelper;
    private SQLiteDatabase mDatabase;

    public UserService(Context context){
        mBillBaseHelper=new BillBaseHelper(context);
    }

    public boolean login(String username,String password){
        SQLiteDatabase sdb=mBillBaseHelper.getReadableDatabase();
        String sql="select * from user where username=? and password=?";
        Cursor cursor=sdb.rawQuery(sql, new String[]{username,password});
        if(cursor.moveToFirst()==true){
            cursor.close();
            return true;
        }
        return false;
    }

    public boolean register(User user){
        SQLiteDatabase sdb=mBillBaseHelper.getReadableDatabase();
        String sql="insert into user(username,password) values(?,?)";
        Object obj[]={user.getUsername(),user.getPassword()};
        sdb.execSQL(sql, obj);
        sdb.close();
        return true;
    }
    public boolean CheckIsDataAlreadyInDBorNot(String username){
        SQLiteDatabase sdb=mBillBaseHelper.getWritableDatabase();
        String Query = "Select * from user where username =?";
        Cursor cursor = sdb.rawQuery(Query,new String[] { username });
        if (cursor.getCount()>0){
            cursor.close();
            return  true;
        }
        cursor.close();
        return false;
    }
    public void build(){
        mDatabase=mBillBaseHelper.getWritableDatabase();
    }
}

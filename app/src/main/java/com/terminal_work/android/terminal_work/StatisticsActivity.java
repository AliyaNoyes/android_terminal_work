package com.terminal_work.android.terminal_work;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListPopupWindow;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

public class StatisticsActivity extends AppCompatActivity {
    private sum_bill_lab mSum_bill_lab;
    private EditText myearEditText;
    private EditText mmonthEditText;
    private EditText mdayEditText;
    private Button mButton1;
    private Button mButton2;
    private Button mButton3;
    private TextView mTextView;
    private Calendar date;
    private daily_bill mDaily_bill;
    private List<daily_bill> mDailyBills;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        myearEditText=(EditText)findViewById(R.id.year_sum);
        mmonthEditText=(EditText)findViewById(R.id.month_sum);
        mdayEditText=(EditText)findViewById(R.id.day_sum);

        mContext = getApplicationContext().getApplicationContext();
        mDatabase=new BillBaseHelper(mContext).getWritableDatabase();
        mButton1=(Button)findViewById(R.id.yearsure_sum);
        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String year = myearEditText.getText().toString();
                if (year.equals("")) {
                    Toast.makeText(StatisticsActivity.this, "请输入年份的查询内容", Toast.LENGTH_SHORT).show();
                } else {
                    if (!(year.length() == 4 || year.length() == 2)) {
                        Toast.makeText(StatisticsActivity.this, "请输入正确格式", Toast.LENGTH_SHORT).show();
                    } else {
                        String Query = "Select * from bill where username =? and year=?";
                        Cursor cursor = mDatabase.rawQuery(Query, new String[]{LoadActivity.Username, year});
                        int count = 0;
                        if (cursor != null && cursor.moveToFirst()) {
                            do {
                                int amount = cursor.getInt(3);
                                int type = cursor.getInt(5);
                                if (type == 1) {
                                    count = amount + count;
                                } else {
                                    count = count - amount;
                                }
                            } while (cursor.moveToNext());
                            cursor.close();
                        }
                        mTextView.setText(year + "年总账单为：" + Integer.toString(count) + "元");
                    }
                }
            }
        });
        mButton2=(Button)findViewById(R.id.monthsure_sum);
        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String year = myearEditText.getText().toString();
                String month = mmonthEditText.getText().toString();
                if (year.equals("")||month.equals("")) {
                    Toast.makeText(StatisticsActivity.this, "请输入年月份的查询内容", Toast.LENGTH_SHORT).show();
                } else {
                    if (!(year.length() == 4 || year.length() == 2)||!(month.length()==2||month.length()==1) ){
                        Toast.makeText(StatisticsActivity.this, "请输入正确格式", Toast.LENGTH_SHORT).show();
                    } else {
                        month=Integer.toString(Integer.parseInt(month) - 1);
                        String Query = "Select * from bill where username =? and year=? and month=?";
                        Cursor cursor = mDatabase.rawQuery(Query, new String[]{LoadActivity.Username, year, month});
                        int count = 0;
                        if (cursor != null && cursor.moveToFirst()) {
                            do {
                                int amount = cursor.getInt(3);
                                int type = cursor.getInt(5);
                                if (type == 1) {
                                    count = amount + count;
                                } else {
                                    count = count - amount;
                                }
                            } while (cursor.moveToNext());
                            cursor.close();
                        }
                        mTextView.setText(year + "年" + month + "月" + "总账单为：" + Integer.toString(count) + "元");
                    }
                }
            }
        });
        mButton3=(Button)findViewById(R.id.daysure_sum);
        mButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String year=myearEditText.getText().toString();
                String month = mmonthEditText.getText().toString();
                String day=mdayEditText.getText().toString();
                if (year.equals("")||month.equals("")||day.equals("")) {
                    Toast.makeText(StatisticsActivity.this, "请输入年月日的查询内容", Toast.LENGTH_SHORT).show();
                } else {
                    if (!(year.length() == 4 || year.length() == 2) || !(month.length() == 2 || month.length() == 1)||!(day.length() == 2 ||day.length() == 1)) {
                        Toast.makeText(StatisticsActivity.this, "请输入正确格式", Toast.LENGTH_SHORT).show();
                    } else {
                        month=Integer.toString(Integer.parseInt(month) - 1);
                        String Query = "Select * from bill where username =? and year=? and month=? and day=?";
                        Cursor cursor = mDatabase.rawQuery(Query, new String[]{LoadActivity.Username, year, month, day});
                        int count = 0;
                        if (cursor != null && cursor.moveToFirst()) {
                            do {
                                int amount = cursor.getInt(3);
                                int type = cursor.getInt(5);
                                if (type == 1) {
                                    count = amount + count;
                                } else {
                                    count = count - amount;
                                }
                            } while (cursor.moveToNext());
                            cursor.close();
                        }
                        mTextView.setText(year + "年" + month + "月" + day + "日" + "总账单为：" + Integer.toString(count) + "元");
                    }
                }
            }
        });
        mTextView=(TextView)findViewById(R.id.count);


    }



}

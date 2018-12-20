package com.terminal_work.android.terminal_work;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Calendar;

public class DailyBillActivity extends AppCompatActivity {

    private Calendar date;
    private Button date_button;
    private RecyclerView mRecyclerView;
    private DailyBillAdapter mAdapter;
    private daily_bill mDaily_bill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily_bill);
        //初始化
        date=Calendar.getInstance();
        mDaily_bill=new daily_bill(date);
        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("trading_project");
        trading_project project=(trading_project) bundle.getSerializable("trading_project");
        mDaily_bill.getBill().add(project);

        mRecyclerView=(RecyclerView)findViewById(R.id.daily_recycle_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mAdapter=new DailyBillAdapter(mDaily_bill);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

        date_button=(Button)findViewById(R.id.button_data_day);
        date_button.setText(date.get(Calendar.YEAR)+"年"+(date.get(Calendar.MONTH)+1)+"月"+date.get(Calendar.DAY_OF_MONTH)+"日");
        //如果从添加页面进入该页面则无需改动，若是查看详情页面进入，则需要锁定button，不允许点击。
        date_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datedialog(v);
            }
        });
    }

    public void datedialog(View v){
        DatePickerDialog datepicker=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                date.set(Calendar.YEAR,year);
                date.set(Calendar.MONTH,month);
                date.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                date_button.setText(date.get(Calendar.YEAR)+"年"+(date.get(Calendar.MONTH)+1)+"月"+date.get(Calendar.DAY_OF_MONTH)+"日");
                mDaily_bill.setDate(date);
            }
        },date.get(Calendar.YEAR),date.get(Calendar.MONTH),date.get(Calendar.DAY_OF_MONTH));
        datepicker.show();
    }
}

package com.terminal_work.android.terminal_work;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.List;

public class DailyBillActivity extends AppCompatActivity {

    private Calendar date;
    private Button date_button;
    private Button date_sure_add_button;
    private RecyclerView mRecyclerView;
    private DailyBillAdapter mAdapter;
    private daily_bill mDaily_bill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily_bill);
        sum_bill_lab sbl=sum_bill_lab.get(DailyBillActivity.this);
        Intent intent=getIntent();
        date=(Calendar) intent.getSerializableExtra("daily_bill_date");
        if(date==null) {
            date = Calendar.getInstance();
            mDaily_bill=new daily_bill(date);
        }
        else
            mDaily_bill=sbl.getDailyBill(date);

        mRecyclerView=(RecyclerView)findViewById(R.id.daily_recycle_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mAdapter=new DailyBillAdapter(mDaily_bill,DailyBillActivity.this);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

        date_button=(Button)findViewById(R.id.button_date_day);
        date_button.setText(date.get(Calendar.YEAR)+"年"+(date.get(Calendar.MONTH)+1)+"月"+date.get(Calendar.DAY_OF_MONTH)+"日");
        //如果从添加页面进入该页面则无需改动，若是查看详情页面进入，则需要锁定button，不允许点击。
        date_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datedialog(v);
            }
        });
        date_sure_add_button=(Button)findViewById(R.id.button_date_sure_add);
        date_sure_add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                Bundle bundle=new Bundle();
                bundle.putSerializable("daily_bill",mDaily_bill);
                intent.putExtras(bundle);
                setResult(1,intent);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode==1){
            Bundle bundle=data.getExtras();
            trading_project project=(trading_project) bundle.getSerializable("trading_project");
            int flag=isInBills(project);
            if(flag!=-1)
                mDaily_bill.getBills().set(flag,project);
            else
                mDaily_bill.getBills().add(project);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

   public int isInBills(trading_project tp){
       for(int i=0;i<mDaily_bill.getBills().size();i++){
           if(mDaily_bill.getBills().get(i).getId().equals(tp.getId())){
               return i;
          }
      }
       return -1;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.add_item,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId()==R.id.add_item){
            Intent intent=new Intent();
            intent.setClass(DailyBillActivity.this,AddProjectActivity.class);
            startActivityForResult(intent,0);
        }
        if(item.getItemId()==R.id.search_item){
            Intent intent=new Intent();
            intent.setClass(DailyBillActivity.this,StatisticsActivity.class);
            startActivityForResult(intent,0);
        }
        return true;
    }

    private void updateUI(){
        if(mAdapter==null){
            mAdapter=new DailyBillAdapter(mDaily_bill,DailyBillActivity.this);
            mRecyclerView.setAdapter(mAdapter);
        }
        else{
            mAdapter.notifyDataSetChanged();
        }
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

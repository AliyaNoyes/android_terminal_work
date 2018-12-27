package com.terminal_work.android.terminal_work;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.List;

public class SumBillActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private SumBillAdapter mAdapter;
    private sum_bill_lab mBillLab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sum_bill);

        mBillLab=sum_bill_lab.get(this);
        List<daily_bill> bills=mBillLab.getDailyBills();

        mRecyclerView=(RecyclerView)findViewById(R.id.sum_recycle_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mAdapter=new SumBillAdapter(mBillLab,SumBillActivity.this);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
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
            intent.setClass(SumBillActivity.this,DailyBillActivity.class);
            startActivityForResult(intent,0);
        }
        if(item.getItemId()==R.id.search_item){
            Intent intent=new Intent();
            intent.setClass(SumBillActivity.this,StatisticsActivity.class);
            startActivityForResult(intent,0);
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode==1){
            Bundle bundle=data.getExtras();
            daily_bill db=(daily_bill) bundle.getSerializable("daily_bill");
            int flag=isInLab(db);
            if(flag!=-1)
                mBillLab.getDailyBills().set(flag,db);
            else
                mBillLab.getDailyBills().add(db);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public int isInLab(daily_bill db){
        for(int i=0;i<mBillLab.getDailyBills().size();i++){
            if(mBillLab.getDailyBills().get(i).getDate().equals(db.getDate()))
                return i;
        }
        return -1;
    }

    private void updateUI(){
        if(mAdapter==null){
            mAdapter=new SumBillAdapter(mBillLab,SumBillActivity.this);
            mRecyclerView.setAdapter(mAdapter);
        }
        else{
            mAdapter.notifyDataSetChanged();
        }
    }
}

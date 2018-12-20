package com.terminal_work.android.terminal_work;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;

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
        mAdapter=new SumBillAdapter(mBillLab);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
    }
}

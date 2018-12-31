package com.terminal_work.android.terminal_work;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;
import java.util.zip.Inflater;

public class SumBillAdapter extends RecyclerView.Adapter<SumBillAdapter.SumBillHolder>{

    private sum_bill_lab mBillLab;
    private Context mContext;

    public SumBillAdapter(sum_bill_lab lab,Context context){
        mBillLab=lab;
        mContext=context;
    }

    @Override
    public SumBillHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sum_bill_item,null,false);
        SumBillAdapter.SumBillHolder holder=new SumBillHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SumBillHolder sumBillHolder, int i) {
        daily_bill db=mBillLab.getDailyBills().get(i);
        sumBillHolder.bind(db);

        sumBillHolder.mTextView.setText(mBillLab.getDailyBills().get(i).getDate().get(Calendar.YEAR)
                +"年"+(mBillLab.getDailyBills().get(i).getDate().get(Calendar.MONTH)+1)
                +"月"+mBillLab.getDailyBills().get(i).getDate().get(Calendar.DAY_OF_MONTH)
                +"日"+"\n"+"账单记录为："+String.format("%.2f",mBillLab.getDailyBills().get(i).getSumAmount())+"元");
    }

    @Override
    public int getItemCount() {
        return mBillLab.getDailyBills().size();
    }

    class SumBillHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView mTextView;
        private daily_bill mdb;

        public SumBillHolder(View itemView) {
            super(itemView);
            mTextView=(TextView) itemView.findViewById(R.id.text_sum_bill);
            itemView.setOnClickListener(this);
        }

        public void bind(daily_bill db){
            mdb=db;
        }

        @Override
        public void onClick(View v) {
            Intent intent=new Intent(mContext,DailyBillActivity.class);
            Activity mActivity=(AppCompatActivity)mContext;
            intent.putExtra("daily_bill_date",mdb.getDate());
            mActivity.startActivityForResult(intent,2);
        }
    }
}
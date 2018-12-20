package com.terminal_work.android.terminal_work;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.zip.Inflater;

public class SumBillAdapter extends RecyclerView.Adapter<SumBillAdapter.SumBillHolder>{

    private sum_bill_lab mBillLab;

    public SumBillAdapter(sum_bill_lab lab){
        mBillLab=lab;
    }

    @Override
    public SumBillHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sum_bill_item,viewGroup,false);
        SumBillAdapter.SumBillHolder holder=new SumBillHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SumBillHolder sumBillHolder, int i) {
        sumBillHolder.mTextView.setText(mBillLab.getDailyBills().get(i).getDate()+"  "+mBillLab.getDailyBills().get(i).getSumAmount());
    }

    @Override
    public int getItemCount() {
        return mBillLab.getDailyBills().size();
    }

    class SumBillHolder extends RecyclerView.ViewHolder{
        TextView mTextView;
        public SumBillHolder(View itemView) {
            super(itemView);
            mTextView=(TextView) itemView.findViewById(R.id.text_sum_bill);
        }
    }
}

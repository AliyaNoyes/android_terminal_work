package com.terminal_work.android.terminal_work;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;

public class DailyBillAdapter extends RecyclerView.Adapter<DailyBillAdapter.DailyBillHolder> {

    private daily_bill mDaily_bill;

    public  DailyBillAdapter(daily_bill db){
        mDaily_bill=db;
    }

    @Override
    public DailyBillHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.daily_bill_item,null,false);
        DailyBillAdapter.DailyBillHolder holder=new DailyBillHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(DailyBillHolder dailyBillHolder, int position) {
        //type and mode is number not string!!!!
        dailyBillHolder.mTextView.setText(
                ""+mDaily_bill.getBill().get(position).getTime().get(Calendar.HOUR_OF_DAY)
                +":" +mDaily_bill.getBill().get(position).getTime().get(Calendar.MINUTE)
                +"  "+mDaily_bill.getBill().get(position).getName()
        +"  "+mDaily_bill.getBill().get(position).getAmount()
                +"  " +mDaily_bill.getBill().get(position).getType()
                +"  "+mDaily_bill.getBill().get(position).getMode());
    }

    @Override
    public int getItemCount() {
        return mDaily_bill.getBill().size();
    }

    class DailyBillHolder extends RecyclerView.ViewHolder{
        TextView mTextView;
        public DailyBillHolder(View itemView) {
            super(itemView);
            mTextView=(TextView) itemView.findViewById(R.id.text_daily_bill_item);
        }
    }
}

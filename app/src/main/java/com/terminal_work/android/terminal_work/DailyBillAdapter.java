package com.terminal_work.android.terminal_work;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;

public class DailyBillAdapter extends RecyclerView.Adapter<DailyBillAdapter.DailyBillHolder> {

    private daily_bill mDaily_bill;
    private Context context;

    public  DailyBillAdapter(daily_bill db,Context context){
        this.context=context;
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

        trading_project tp=mDaily_bill.getBills().get(position);
        dailyBillHolder.bind(tp);
        //将类型和支出方式改为相应文字
        String type,mode;
        int type_int=mDaily_bill.getBills().get(position).getType();
        int mode_int=mDaily_bill.getBills().get(position).getMode();
        if(type_int==1)
            type="收入";
        else
            type="支出";
        if(mode_int==-1)
            mode="现金";
        else
            mode="信用卡";
        Log.d("777777777",type_int+"   "+type);
        dailyBillHolder.mTextView.setText(
                ""+mDaily_bill.getBills().get(position).getTime().get(Calendar.HOUR_OF_DAY)
                +":" +mDaily_bill.getBills().get(position).getTime().get(Calendar.MINUTE)
                +"  "+mDaily_bill.getBills().get(position).getName()
                +"  " +type +"： "+mDaily_bill.getBills().get(position).getAmount()+"元   "+"支付方式为： "+mode);
    }

    @Override
    public int getItemCount() {
        return mDaily_bill.getBills().size();
    }

    class DailyBillHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView mTextView;
        private trading_project mtp;
        public DailyBillHolder(View itemView) {
            super(itemView);
            mTextView=(TextView) itemView.findViewById(R.id.text_daily_bill_item);
            itemView.setOnClickListener(this);
        }

        public void bind(trading_project tp){
            mtp=tp;
        }

        @Override
        public void onClick(View v) {
            Intent intent=new Intent(context,AddProjectActivity.class);
            intent.putExtra("tp",mtp);
            Activity mActivity=(AppCompatActivity)context;
            mActivity.startActivityForResult(intent,0);
        }
    }

}

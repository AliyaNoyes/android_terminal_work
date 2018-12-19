package com.terminal_work.android.terminal_work;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AddProjectActivity extends AppCompatActivity {

    private final int INCOMING=1;
    private final int OUTGOING=-1;
    private final int CRASH=0;
    private final int CREDIT=1;

    private EditText edit_name;
    private EditText edit_amount;
    private RadioGroup radio_type;
    private RadioGroup radio_mode_pay;
    private RadioButton radio_incoming;
    private RadioButton radio_outgoing;
    private RadioButton radio_crash;
    private RadioButton radio_credit;
    private Button sure_add_button;
    private trading_project mProject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_project);

        edit_name=(EditText)findViewById(R.id.edit_name);
        edit_amount=(EditText)findViewById(R.id.edit_amount);
        radio_incoming=(RadioButton)findViewById(R.id.radio_incoming);
        radio_outgoing=(RadioButton)findViewById(R.id.radio_outgoing);
        radio_crash=(RadioButton)findViewById(R.id.radio_crash);
        radio_credit=(RadioButton)findViewById(R.id.radio_credit);
        sure_add_button=(Button)findViewById(R.id.sure_add);
        radio_type=(RadioGroup)findViewById(R.id.radio_type);
        radio_mode_pay=(RadioGroup)findViewById(R.id.radio_mode_of_payment);
        mProject=new trading_project();

        radio_type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int id=group.getCheckedRadioButtonId();
                if(radio_incoming.getId()==id)
                    mProject.setType(INCOMING);
                if(radio_outgoing.getId()==id)
                    mProject.setType(OUTGOING);
            }
        });

        radio_mode_pay.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int id=group.getCheckedRadioButtonId();
                if(radio_crash.getId()==id)
                    mProject.setType(CRASH);
                if(radio_credit.getId()==id)
                    mProject.setType(CREDIT);
            }
        });

        sure_add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProject.setName(edit_name.getText().toString());
                if(edit_amount!=null){
                    String str=edit_amount.getText().toString();
                    if(str!=null&&str.length()>0){
                        mProject.setAmount(Integer.parseInt(str));
                    }
                }
                Intent intent=new Intent(AddProjectActivity.this,DailyBillActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("s",mProject);
            }
        });
    }
}

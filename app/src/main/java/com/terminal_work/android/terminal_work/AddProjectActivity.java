package com.terminal_work.android.terminal_work;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.UUID;

public class AddProjectActivity extends AppCompatActivity {

    private final int INCOMING=1;
    private final int OUTGOING=-1;
    private final int CRASH=-1;
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
    private Button project_button;
    private trading_project mProject;
    private Calendar time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_project);

        Intent intent=getIntent();
        trading_project tp=(trading_project) intent.getSerializableExtra("tp");
        if(tp==null){
            mProject=new trading_project();
            mProject.setTime(Calendar.getInstance());
        }
        else{
            mProject=tp;
        }

        edit_name=(EditText)findViewById(R.id.edit_name);
        edit_amount=(EditText)findViewById(R.id.edit_amount);
        radio_incoming=(RadioButton)findViewById(R.id.radio_incoming);
        radio_outgoing=(RadioButton)findViewById(R.id.radio_outgoing);
        radio_crash=(RadioButton)findViewById(R.id.radio_crash);
        radio_credit=(RadioButton)findViewById(R.id.radio_credit);
        sure_add_button=(Button)findViewById(R.id.sure_add);
        radio_type=(RadioGroup)findViewById(R.id.radio_type);
        radio_mode_pay=(RadioGroup)findViewById(R.id.radio_mode_of_payment);
        project_button=(Button)findViewById(R.id.button_project_time);

        Log.d("7777777777"," "+mProject.getName()+mProject.getAmount()+" "+mProject.getType()+" "+mProject.getMode()+" "+mProject.getTime());

        time =mProject.getTime();
        String name_str=mProject.getName();
        String amount_str=mProject.getAmount()+"";
        edit_name.setText(name_str);
        edit_amount.setText(amount_str);
        project_button.setText(""+time.get(Calendar.HOUR_OF_DAY)+":"+time.get(Calendar.MINUTE));
        if(mProject.getType()==1)
            radio_type.check(radio_incoming.getId());
        else if(mProject.getType()==-1)
            radio_type.check(radio_outgoing.getId());
        else
            radio_type.clearCheck();
        if(mProject.getMode()==1)
            radio_mode_pay.check(radio_credit.getId());
        else if(mProject.getMode()==-1)
            radio_mode_pay.check(radio_crash.getId());
        else
            radio_mode_pay.clearCheck();

        radio_type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int id=group.getCheckedRadioButtonId();
                if(radio_incoming.getId()==id) {
                    mProject.setType(INCOMING);
                }
                else if(radio_outgoing.getId()==id)
                    mProject.setType(OUTGOING);
            }
        });

        radio_mode_pay.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int id=group.getCheckedRadioButtonId();
                if(radio_crash.getId()==id)
                    mProject.setMode(CRASH);
                if(radio_credit.getId()==id)
                    mProject.setMode(CREDIT);
            }
        });

        project_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timedialog(v);
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
                mProject.setTime(time);
                Intent intent=new Intent();
                Bundle bundle=new Bundle();
                bundle.putSerializable("trading_project",mProject);
                intent.putExtras(bundle);
                setResult(1,intent);
                finish();
            }
        });
    }

    public void timedialog(View v){
        TimePickerDialog timepicker=new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                time.set(Calendar.HOUR_OF_DAY,hourOfDay);
                time.set(Calendar.MINUTE,minute);
                project_button.setText(""+time.get(Calendar.HOUR_OF_DAY)+":"+time.get(Calendar.MINUTE));
            }
        },time.get(Calendar.HOUR_OF_DAY),time.get(Calendar.MINUTE),true);
        timepicker.setTitle("选择你要设定的时间");
        timepicker.show();
    }
}

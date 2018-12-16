package com.terminal_work.android.terminal_work;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;

public class AddProjectActivity extends AppCompatActivity {

    private EditText edit_name;
    private EditText edit_amount;
    private RadioButton radio_incoming;
    private RadioButton radio_outgoing;
    private RadioButton radio_crash;
    private RadioButton radio_credit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_project);


    }
}

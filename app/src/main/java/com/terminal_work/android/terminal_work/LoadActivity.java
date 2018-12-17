package com.terminal_work.android.terminal_work;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoadActivity extends AppCompatActivity {

    private Button mloadButton;
    private Button mregisterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load);

        mloadButton=(Button)findViewById(R.id.loadbutton);
        mregisterButton=(Button)findViewById(R.id.registerbutton);
        mregisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoadActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}

package com.terminal_work.android.terminal_work;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    private Button mregisterButton1;
    private EditText musernameEditText;
    private EditText mpasswordEditText;
    private UserService mUserService;
    private Context mContext;
    private SQLiteDatabase mDatabase;
//    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        musernameEditText=(EditText)findViewById(R.id.edit_uesrname);
        mpasswordEditText=(EditText)findViewById(R.id.edit_password);

        mregisterButton1=(Button)findViewById(R.id.registerbutton1);
        mregisterButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext= getApplicationContext().getApplicationContext();
                mUserService=new UserService(mContext);
                mUserService.build();
                User mUser=new User();
                String name=musernameEditText.getText().toString();
                String password=mpasswordEditText.getText().toString();
                mUser.setUsername(name);
                mUser.setPassword(password);
                if(mUserService.CheckIsDataAlreadyInDBorNot(name)){
                    Toast.makeText(RegisterActivity.this,"该用户名已被注册，请重新输入",Toast.LENGTH_SHORT).show();
                }else {
                    if(mUserService.register(mUser)) {
                        Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    }
                    Intent intent=new Intent(RegisterActivity.this,LoadActivity.class);
                    intent.putExtra("username",name);
                    intent.putExtra("pw",password);
                    startActivity(intent);
                }

            }
        });
    }
}

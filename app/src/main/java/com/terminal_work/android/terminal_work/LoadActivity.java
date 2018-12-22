package com.terminal_work.android.terminal_work;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoadActivity extends AppCompatActivity {

    private Button mloadButton;
    private Button mregisterButton;
    private EditText usename;
    private EditText password;
    private Context mContext;
    private UserService mUserService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load);
        String name=getIntent().getStringExtra("username");
        String pw=getIntent().getStringExtra("pw");
        usename=(EditText)findViewById(R.id.edit_uesrname1);
        password=(EditText)findViewById(R.id.edit_password1);
        mloadButton=(Button)findViewById(R.id.loadbutton);
        mregisterButton=(Button)findViewById(R.id.registerbutton);
        mregisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoadActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        mloadButton=(Button)findViewById(R.id.loadbutton);
        mloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext = getApplicationContext().getApplicationContext();
                mUserService = new UserService(mContext);
                String name = usename.getText().toString();
                String pw = password.getText().toString();
                if (name.equals("") || pw.equals("")) {
                    Toast.makeText(LoadActivity.this, "请输入账户或密码", Toast.LENGTH_SHORT).show();
                } else {

                    if (mUserService.login(name, pw)) {
                        Toast.makeText(LoadActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoadActivity.this, AddProjectActivity.class);
                        startActivity(intent);
                    } else {
                        if (mUserService.CheckIsDataAlreadyInDBorNot(name)) {
                            Toast.makeText(LoadActivity.this, "密码不正确", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoadActivity.this, "用户不存在", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
        update(name,pw);
    }

    private void update(String name,String pw){
        usename.setText(name);
        password.setText(pw);
    }
}

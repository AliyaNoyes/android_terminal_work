package com.terminal_work.android.terminal_work;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class LoadActivity extends AppCompatActivity {

    private Button mloadButton;
    private Button mregisterButton;
    private EditText usename;
    private EditText password;
    private Context mContext;
    private UserService mUserService;
    public static String Username;
    private sum_bill_lab mBillLab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load);
        usename=(EditText)findViewById(R.id.edit_uesrname1);
        password=(EditText)findViewById(R.id.edit_password1);
        mloadButton=(Button)findViewById(R.id.loadbutton);
        mregisterButton=(Button)findViewById(R.id.registerbutton);
        mregisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoadActivity.this,RegisterActivity.class);
                startActivityForResult(intent,0);
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
                Username=name;
                if (name.equals("") || pw.equals("")) {
                    Toast.makeText(LoadActivity.this, "请输入账户或密码", Toast.LENGTH_SHORT).show();
                } else {

                    if (mUserService.login(name, pw)) {
                        mBillLab=sum_bill_lab.get(LoadActivity.this);
                        List<daily_bill> bills=mBillLab.getDailyBills();
                        bills.clear();
                        mBillLab.getdatafromdb();
                        Toast.makeText(LoadActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoadActivity.this, SumBillActivity.class);
                        startActivityForResult(intent,1);
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

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode!=1){
            return;
        }
        if(requestCode==0){
            usename.setText(data.getStringExtra("username"));
            password.setText(data.getStringExtra("pw"));
        }
    }
}

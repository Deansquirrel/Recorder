package com.yuansong.recorder;

import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yuansong.recorder.Common.CommonFun;
import com.yuansong.recorder.Common.Global;

public class LoginActivity extends BaseActivity {

    private Toolbar mToolbar = null;
    private EditText mEditTextUserName = null;
    private EditText mEditTextPassword = null;
    private Button mBtnLogin = null;
    private TextView mTextViewVersion = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("LoginActivity", "onCreate");
        setContentView(R.layout.activity_login);

        mToolbar = findViewById(R.id.toolbar);
        mEditTextUserName = findViewById(R.id.editTextUser);
        mEditTextPassword = findViewById(R.id.editTextPwd);
        mBtnLogin = findViewById(R.id.btnLogin);
        mTextViewVersion = findViewById(R.id.textViewVersion);

        mToolbar.setTitle("Recorder");
        setSupportActionBar(mToolbar);

        mBtnLogin.setOnClickListener(new BtnLoginListener());
        mBtnLogin.setText("Login");

        mTextViewVersion.setText(Global.CLIENT_VERSION);

        CommonFun.setFocus(LoginActivity.this,mEditTextUserName);

//        CommonFun.showActivity(LoginActivity.this,MainActivity.class,true);
    }

    private class BtnLoginListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Log.i("UserName",mEditTextUserName.getText().toString());
            Log.i("Password",mEditTextPassword.getText().toString());
            String mUserName = mEditTextUserName.getText().toString();
            String mUserPwd = mEditTextPassword.getText().toString();
            mBtnLogin.setEnabled(false);
            if(checkUser(mUserName, mUserPwd)){
                CommonFun.showActivity(LoginActivity.this,MainActivity.class,true);
            }
            else{
                CommonFun.showMsg(LoginActivity.this,"用户名或密码错误");
                mBtnLogin.setEnabled(true);
            }
        }
    }

    private boolean checkUser(String userName,String userPwd){
        if(userName.equals("yuansong") && userPwd.equals("yuansong")){
            return true;
        }
        else{
            return false;
        }
    }
}

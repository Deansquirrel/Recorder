package com.yuansong.recorder;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.yuansong.recorder.AndroidFeatures.SMSHelper;
import com.yuansong.recorder.Common.CommonFun;

public class TestActivity extends BaseActivity {

    private Toolbar mToolbar = null;
    private EditText mEditTextTele = null;
    private EditText mEditTextMessage = null;
    private Button mBtnTest = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        mToolbar = findViewById(R.id.toolbar);
        mBtnTest = findViewById(R.id.btnTest);
        mEditTextTele = findViewById(R.id.editTextTele);
        mEditTextMessage = findViewById(R.id.editTextMessage);

        mToolbar.setTitle("Test");

        mBtnTest.setText("Button Test");
        mBtnTest.setOnClickListener(new BtnTestClickListener());

        CommonFun.setFocus(this,mEditTextTele);
    }

    private class BtnTestClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            subTest();
        }
    }

    private void subTest(){
        Log.i("TestActivity","subTest");
        SMSHelper mSMSHelper = new SMSHelper();
//        mSMSHelper.sendMessage("15555215556","Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。Test Message。");
        String tele = mEditTextTele.getText().toString();
        tele = tele.trim();
        String message = mEditTextMessage.getText().toString();
        message = message.trim();
        if(tele.length() > 0 && message.length() > 0){
            mSMSHelper.sendMessage(tele,message);
        }
        else{
            CommonFun.showMsg(TestActivity.this,"电话或信息内容不能为空");
        }

        mSMSHelper.sendMessagePre(TestActivity.this,tele,message);
    }
}

package com.yuansong.recorder.Activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.yuansong.recorder.Common.CommonFun;
import com.yuansong.recorder.R;

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
        setSupportActionBar(mToolbar);

        mBtnTest.setText("Button Test");
        mBtnTest.setOnClickListener(new BtnTestClickListener());

        CommonFun.setFocus(this,mEditTextTele);

        this.showBackOption();
    }

    private class BtnTestClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            subTest();
        }
    }

    private void subTest(){
        Log.i("TestActivity","subTest");
        Log.i("phoneNumber",mEditTextTele.getText().toString().trim());
        Log.i("message",mEditTextMessage.getText().toString().trim());
    }
}

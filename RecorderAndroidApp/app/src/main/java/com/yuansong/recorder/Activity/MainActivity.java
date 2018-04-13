package com.yuansong.recorder.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.support.v7.widget.Toolbar;

import com.yuansong.recorder.Common.CommonFun;
import com.yuansong.recorder.DB.ConfigDBHelper;
import com.yuansong.recorder.R;

public class MainActivity extends BaseActivity {

    private Toolbar mToolbar = null;
    private Button mBtnAddRecord = null;
    private Button mBtnRecordList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("MainActivity", "onCreate");
        setContentView(R.layout.activity_main);

        Log.i("WebAddress", ConfigDBHelper.getWebAddress());

        mToolbar = findViewById(R.id.toolbar);
        mBtnAddRecord = findViewById(R.id.btnAddRecord);
        mBtnRecordList = findViewById(R.id.btnRecordList);

        mToolbar.setTitle("Recorder");
        setSupportActionBar(mToolbar);

        mBtnAddRecord.setText("增加记录");
        mBtnAddRecord.setOnClickListener(new BtnAddRecordListener());

        mBtnRecordList.setText("记录列表");
        mBtnRecordList.setOnClickListener(new BtnRecordListListener());

    }

    private class BtnAddRecordListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Log.i("View.OnClickListener","BtnAddRecordListener");
            CommonFun.showActivity(MainActivity.this,RecordAddActivity.class,false);
        }
    }

    private class BtnRecordListListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Log.i("View.OnClickListener","BtnRecordListListener");
            CommonFun.showActivity(MainActivity.this,RecordListActivity.class,false);
        }
    }
}

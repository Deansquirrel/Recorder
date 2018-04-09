package com.yuansong.recorder;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import android.support.v7.widget.Toolbar;

import com.yuansong.recorder.DB.ConfigDBHelper;

public class MainActivity extends BaseActivity {

    private Toolbar mToolbar = null;
    private Button btnGotoLogin = null;
    private Button btnGotoSysConfig = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("MainActivity", "onCreate");
        setContentView(R.layout.activity_main);

        Log.i("WebAddress", ConfigDBHelper.getWebAddress());

        mToolbar = findViewById(R.id.toolbar);
        btnGotoLogin = findViewById(R.id.btn_login);
        btnGotoSysConfig = findViewById(R.id.btn_sys_config);


        mToolbar.setTitle("Toolbar Title");
        setSupportActionBar(mToolbar);
    }


}

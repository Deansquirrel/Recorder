package com.yuansong.recorder;

import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.util.Log;

public class LoginActivity extends BaseActivity {

    private Toolbar mToolbar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("LoginActivity", "onCreate");
        setContentView(R.layout.activity_login);

        mToolbar = findViewById(R.id.toolbar);

        mToolbar.setTitle("Recorder");
        setSupportActionBar(mToolbar);
    }
}

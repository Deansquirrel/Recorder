package com.yuansong.recorder.Activity;

import android.support.v7.widget.Toolbar;

import android.os.Bundle;

import com.yuansong.recorder.R;

public class RecordAddActivity extends BaseActivity {

    private Toolbar mToolbar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_add);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);


        showBackOption();
    }
}

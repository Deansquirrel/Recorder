package com.yuansong.recorder.Activity;

import android.os.Bundle;

import android.support.v7.widget.Toolbar;

import com.yuansong.recorder.R;

public class RecordListActivity extends BaseActivity {

    private Toolbar mToolbar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_list);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        setDarkWindow();
        showBackOption();
    }
}

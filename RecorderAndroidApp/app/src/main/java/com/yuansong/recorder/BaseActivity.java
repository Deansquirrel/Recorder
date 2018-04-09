package com.yuansong.recorder;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;

import com.yuansong.recorder.DB.CacheDBHelper;
import com.yuansong.recorder.DB.ConfigDBHelper;
import com.yuansong.recorder.Common.Global;

import java.util.Calendar;

/**
 * Created by yuansong on 2018/3/5.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private CacheDBHelper mCacheDBHelper = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("BaseActivity","onCreate");
        mCacheDBHelper = CacheDBHelper.getInstance(this);

        //竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //标题栏置白
        Window window = this.getWindow();
        if(window != null){
            window.setStatusBarColor(this.getResources().getColor(R.color.whiteColor));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("BaseActivity","onPause");
        mCacheDBHelper.saveCacheUser();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("BaseActivity","onResume");
        mCacheDBHelper.refreshCacheUser();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            clickActionBarBackBtn();
            return  true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void clickActionBarBackBtn(){
        if(BaseActivity.this.isTaskRoot()){
            moveTaskToBack(false);
        }
        else{
            finish();
        }
    };
}

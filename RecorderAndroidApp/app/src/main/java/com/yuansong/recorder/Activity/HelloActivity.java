package com.yuansong.recorder.Activity;

import android.os.Bundle;
import android.util.Log;

import com.yuansong.recorder.Common.CommonFun;
import com.yuansong.recorder.Common.Global;
import com.yuansong.recorder.DB.CacheDBHelper;
import com.yuansong.recorder.DB.ConfigDBHelper;
import com.yuansong.recorder.R;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class HelloActivity extends BaseActivity {

    private ConfigDBHelper mConfigDBHelper = null;

    private Timer mTimer = new Timer();
    private TimerTask mTimerTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("HelloActivity","onCreate");
        setContentView(R.layout.activity_hello);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("HelloActivity","onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("HelloActivity","onResume");
        this.checkConfig();

        mTimerTask = new TimerTask() {
            @Override
            public void run() {
                //------------------------------------------------------------------------------------------
                //跳转测试
//                CommonFun.showActivity(HelloActivity.this,LoginActivity.class,true);
                CommonFun.showActivity(HelloActivity.this,MainActivity.class,true);
//                CommonFun.showActivity(HelloActivity.this,RecordAddActivity.class,false);
//                CommonFun.showActivity(HelloActivity.this,SysConfigActivity.class,true);
//                CommonFun.showActivity(HelloActivity.this,TestActivity.class,true);
                //------------------------------------------------------------------------------------------

//                if(reLoginCheck()){
//                    //跳转至Login
//                    CommonFun.showActivity(HelloActivity.this,LoginActivity.class,true);
//                }
//                else {
//                    //跳转至Main
//                    CommonFun.showActivity(HelloActivity.this,MainActivity.class,true);
//                }
            }
        };
        mTimer.schedule(mTimerTask,1000);


    }

    private boolean reLoginCheck(){
        Log.i("HelloActivity","reLoginCheck");
        if(CacheDBHelper.getUserId() == -1){
            return true;
        }
        Calendar calendar = CacheDBHelper.getUpdateTime();
        calendar.add(Calendar.DAY_OF_MONTH,Global.RELOGIN_DAYS);
        boolean result = Calendar.getInstance().after(calendar);
        Log.i("HelloActivity","reLoginCheck" + " | " + String.valueOf(result));
//        return result;
        return true;
    }

    private void checkConfig(){
        mConfigDBHelper = ConfigDBHelper.getInstance(this);
        mConfigDBHelper.refreshCacheConfig();
        if(ConfigDBHelper.getWebAddress().equals("")){
            mConfigDBHelper.updateCacheConfig("http://192.168.1.103:8080/proj");
            mConfigDBHelper.saveCacheConfig();
        }
    }
}

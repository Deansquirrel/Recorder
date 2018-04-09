package com.yuansong.recorder;

import android.os.Bundle;
import android.util.Log;

import com.yuansong.recorder.Common.CommonFun;
import com.yuansong.recorder.Common.Global;
import com.yuansong.recorder.DB.CacheDBHelper;
import com.yuansong.recorder.DB.ConfigDBHelper;

import java.util.Calendar;

public class HelloActivity extends BaseActivity {

    private ConfigDBHelper mConfigDBHelper = null;

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

        //------------------------------------------------------------------------------------------
        //跳转测试
//        CommonFun.showActivity(this,LoginActivity.class,true);
//        CommonFun.showActivity(this,MainActivity.class,true);
//        CommonFun.showActivity(this,SysConfigActivity.class,true);
        //------------------------------------------------------------------------------------------

        if(reLoginCheck()){
            //跳转至Login
            CommonFun.showActivity(this,LoginActivity.class,true);
        }
        else {
            //跳转至Main
            CommonFun.showActivity(this,MainActivity.class,true);
        }
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

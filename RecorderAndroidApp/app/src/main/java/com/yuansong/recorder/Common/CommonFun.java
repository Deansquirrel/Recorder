package com.yuansong.recorder.Common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.support.v7.app.AppCompatActivity;

import com.yuansong.recorder.HelloActivity;
import com.yuansong.recorder.LoginActivity;

/**
 * Created by yuansong on 2018/3/9.
 */

public class CommonFun {

    public static String createNewGuid(){
        return java.util.UUID.randomUUID().toString().toUpperCase();
    }

    public static void showActivity(AppCompatActivity context, Class<?> cls, boolean isQuit){
        Log.i("CommonFun","showActivity|" + context.getClass().toString() + "|" + cls.toString() + "|" + String.valueOf(isQuit));
        final Intent it = new Intent(context,cls);
        if(isQuit){
            context.finish();
        }
        context.startActivity(it);
    }
}

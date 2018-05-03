package com.yuansong.recorder.Common;

import android.content.Intent;
import android.util.Log;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.DatePicker.OnDateChangedListener;

import com.yuansong.recorder.Dialog.DatePickDlg;
import com.yuansong.recorder.Dialog.ListPickDlg;

import java.util.Map;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by yuansong on 2018/3/9.
 */

public class CommonFun {

    private static Timer mTimer = new Timer();
    private static TimerTask mTimerTask = null;

    private CommonFun(){}

    public static String createNewGuid(){
        return java.util.UUID.randomUUID().toString().toUpperCase();
    }

    public static void showActivity(final AppCompatActivity context, Class<?> cls, boolean isQuit){
        Log.i("CommonFun","showActivity|" + context.getClass().toString() + "|" + cls.toString() + "|" + String.valueOf(isQuit));
        final Intent it = new Intent(context,cls);
        context.startActivity(it);
        if(isQuit){
            mTimerTask = new TimerTask() {
                @Override
                public void run() {
                    context.finish();
                }
            };
            mTimer.schedule(mTimerTask,100);
        }
    }

    public static void setFocus(AppCompatActivity activity, EditText et){
        //设置焦点
        et.setFocusable(true);
        et.setFocusableInTouchMode(true);
        et.requestFocus();
        //弹出键盘
//        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }

    public static void showMsg(AppCompatActivity activity, String msg){
//        Toast.makeText(activity.getApplicationContext(), msg,Toast.LENGTH_SHORT).show();
        if("main".equals(Thread.currentThread().getName())){
            Toast.makeText(activity.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        }else{
            final AppCompatActivity mAct = activity;
            final String mMsg = msg;
            // 子线程
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(mAct.getApplicationContext(), mMsg, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public static void showError(AppCompatActivity activity,String msg,boolean isQuit){
        CommonFun.showMsg(activity,msg);
        if(isQuit){
            activity.finish();
        }
    }

    public static void showDatePickDlg(AppCompatActivity activity,
                                       Calendar calendar,
                                       DatePicker.OnDateChangedListener listener){

        DatePickDlg dpd = new DatePickDlg();
        dpd.setCurrCalendar(calendar);
        dpd.setOnDateChangedListener(listener);
        dpd.show(activity.getSupportFragmentManager(),"DateSelect");
    }

    public static void showListPickDlg(AppCompatActivity activity,
                                       String title,
                                       Map<String,String> data,
                                       String currKey,
                                       ListPickDlg.OnDataSelectListener listener){
        ListPickDlg lpd = new ListPickDlg();
        lpd.setTitle(title);
        lpd.setCurrKey(currKey);
        lpd.setData(data);
        lpd.setOnDataSelectListener(listener);
        lpd.show(activity.getSupportFragmentManager(),"ListSelect");
    }

}

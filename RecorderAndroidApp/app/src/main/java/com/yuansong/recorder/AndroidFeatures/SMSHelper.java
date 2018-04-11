package com.yuansong.recorder.AndroidFeatures;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

public class SMSHelper{

    public void sendMessage(String phoneNumber,String message){
        Log.i("phoneNumber",phoneNumber);
        Log.i("message",message);

        SmsManager smsManager = SmsManager.getDefault();

        List<String> sms = smsManager.divideMessage(message);
        for (String msg :sms){
            smsManager.sendTextMessage(phoneNumber,null,msg,null,null);
        }
    }

    public void sendMessagePre(Activity activity, String phoneNumber, String message){
        Intent smsIntent = new Intent(Intent.ACTION_VIEW);
        smsIntent.setData(Uri.parse("smsto:"));
        smsIntent.setType("vnd.android-dir/mms-sms");

        smsIntent.putExtra("address"  , new String ("0123456789"));
        smsIntent.putExtra("sms_body"  , "Test SMS to Angilla");
        try {
            activity.startActivity(smsIntent);
            Log.i("Finished sending SMS...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(activity,"SMS faild, please try again later.", Toast.LENGTH_SHORT).show();
        }
    }

}

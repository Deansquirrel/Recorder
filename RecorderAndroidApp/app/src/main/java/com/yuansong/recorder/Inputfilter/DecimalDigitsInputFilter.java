package com.yuansong.recorder.Inputfilter;

import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;

/**
 * Created by yuansong on 2018/2/2.
 */

public class DecimalDigitsInputFilter implements InputFilter {

    private final int decimalDigits;

    public DecimalDigitsInputFilter(int decimalDigits) {
        this.decimalDigits = decimalDigits;
    }

    @Override
    public CharSequence filter(CharSequence source,
                               int start,
                               int end,
                               Spanned dest,
                               int d_start,
                               int d_end) {

        //删除
        if(start == end && d_start != d_end){
            return null;
        }
        //添加（起始为空，添加小数点，自动补0）
        if(dest.equals("")){
            if(source.equals(".")){
                return "0.";
            }
            else{
                return null;
            }
        }

        //添加（起始不为空）
        String result = getResult(source,start,end,dest,d_start,d_end);
        int dotPost = getDotPost(result);

        if((dotPost >= 0) && ((result.length() - dotPost - decimalDigits - 1) > 0)){
            return "";
        }
        return null;
    }

    private String getResult(CharSequence source,
                              int start,
                              int end,
                              Spanned dest,
                              int d_start,
                              int d_end){
        String result = "";
        if(d_start > 0){
            result = result + dest.subSequence(0,d_start);
        }
        result = result + source.toString();
        if(d_end < dest.length()){
            result = result + dest.subSequence(d_end,dest.length());
        }
        return result;
    }


    private int getDotPost(String dest){
        //定位小数点i
        for (int i = 0; i < dest.length(); i++) {
            char c = dest.charAt(i);
            if (c == '.') {
                return i;
            }
        }
        return -1;
    }
}
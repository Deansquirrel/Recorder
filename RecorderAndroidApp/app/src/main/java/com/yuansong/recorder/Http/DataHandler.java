package com.yuansong.recorder.Http;

import android.util.Log;

import com.google.gson.Gson;
import com.yuansong.common.DateTool;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataHandler {

    private static Gson mGson = new Gson();

    /**
     * Map<String,String> CategoryList
     * @param categoryTypeId
     * @return
     */
    public static String getCategoryList(int categoryTypeId){
        Log.i("DataHandler","getCategoryList");
        Log.i("categoryTypeId",String.valueOf(categoryTypeId));

        Map<String,String> resultMap = new HashMap<>();
        String type = "";
        type = String.valueOf(categoryTypeId);
        for(int i = 0;i<20;i++){
            resultMap.put(String.valueOf(i),"分类 - " + type + " - " + String.valueOf(i));
        }
        return mGson.toJson(resultMap);
    }

    /**
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static String getRecordList(Calendar startDate,Calendar endDate){
        Log.i("DataHandler","getRecordList");
        Log.i("startDate", DateTool.getDateStr(startDate.getTime(),"yyyy-MM-dd"));
        Log.i("endDate", DateTool.getDateStr(endDate.getTime(),"yyyy-MM-dd"));

        List<Map<String,String>> resultList = new ArrayList();
        for(int i=0;i<10;i++){
            Map<String,String> record = new HashMap<String,String>();

//            private String mDate = "";
//            private String mMoney = "";
//            private String mCategory = "";
//            private String mRemark = "";
//            private String mAddTime = "";

            record.put("date", DateTool.getDateStr("yyyy-MM-dd"));
            record.put("money", String.valueOf(i));
            record.put("categoryId",String.valueOf(i));
            record.put("categoryName","Category - " + String.valueOf(i));
            record.put("remark","Remark - " + String.valueOf(i));
            record.put("addTime", DateTool.getDateStr("yyyy-MM-dd HH:SS"));

            resultList.add(record);
        }
        return mGson.toJson(resultList);
    }
}

package com.yuansong.recorder.Http;

import java.util.HashMap;
import java.util.Map;

public class DataHander {

    public static Map<String,String> getCategoryList(int categoryTypeId){
        Map<String,String> result = new HashMap<>();
        String type = "";
        type = String.valueOf(categoryTypeId);
        for(int i = 0;i<20;i++){
            result.put(String.valueOf(i),"分类 - " + type + " - " + String.valueOf(i));
        }
        return result;
    }
}

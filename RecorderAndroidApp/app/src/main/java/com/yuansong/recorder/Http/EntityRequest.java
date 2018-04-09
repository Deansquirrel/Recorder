package com.yuansong.recorder.Http;

import com.google.gson.Gson;
import com.yuansong.recorder.Common.CommonFun;
import com.yuansong.recorder.Common.Global;

/**
 * Created by yuansong on 2018/3/9.
 */

public class EntityRequest {

    private String mRequestGuid;
    private String mClientName;
    private String mClientVersion;
    private Object mData;

    public EntityRequest(String requestGuid, Object data){
        mRequestGuid = requestGuid;
        mData = data;
    }

    public String getFormatString(){
        mClientName = Global.CLIENT_NAME;
        mClientVersion = Global.CLIENT_VERSION;
        Gson mGson = new Gson();
        return mGson.toJson(this);
    }
}
package com.yuansong.recorder.Http;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Created by yuansong on 2018/3/9.
 */

public class NetInterface {

    private String mAddress = "";
    private String mRequestMethod = "GET";
    private String mData = "";
    private int mTimeout = 8 * 1000;

    private OnGetHttpDataListener mBack = null;

    public interface OnGetHttpDataListener {
        void onGetHttpData(String data);
        void onGetError(Exception ex);
        void onPreExecute();
        void onPostExecute();
    }

    public void getHttpData(String address, OnGetHttpDataListener listener) {

    }

    public void getHttpData(String address, Map<String,String> urlPar, OnGetHttpDataListener listener){

    }

    public void getHttpData(String address, String requestMethod, String data, OnGetHttpDataListener listener) {

    }

    private class HttpHandleGetWorker extends AsyncTask<Void, Void, Void> {

        private boolean hasError = false;
        private String mResponse = null;
        private Exception mEx = null;

        @Override
        protected Void doInBackground(Void... voids) {

            if(mBack == null){
                return null;
            }

            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try{
                URL url = new URL(mAddress);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(mTimeout);
                connection.setReadTimeout(mTimeout);
                connection.setUseCaches(false);
                connection.setRequestProperty("Connection", "Keep-Alive");
                connection.setRequestProperty("Charset", "UTF-8");

                InputStream in = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(in));
                StringBuilder response = new StringBuilder();
                String line;
                while((line = reader.readLine())!= null){
                    response.append(line);
                }
                Log.i("msg",response.toString());
                mResponse = response.toString();
            }catch (Exception e){
                e.printStackTrace();
                hasError = true;
                mEx = e;
            }finally {
                if(reader != null){
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        hasError = true;
                        mEx = e;
                    }
                }
                if(connection != null){
                    connection.disconnect();
                }
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if(mBack != null){
                mBack.onPreExecute();
            }
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(mBack != null){
                if(hasError){
                    mBack.onGetError(mEx);
                }
                else{
                    mBack.onGetHttpData(mResponse);
                }
                mBack.onPostExecute();
            }
        }
    }
}

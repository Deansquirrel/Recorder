package com.yuansong.recorder.Activity;

import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yuansong.common.DateTool;
import com.yuansong.recorder.Common.CommonFun;
import com.yuansong.recorder.Http.DataHandler;
import com.yuansong.recorder.R;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class RecordListActivity extends BaseActivity {

    private Toolbar mToolbar = null;

    private TextView mTextViewStartDate = null;
    private TextView mTextViewEndDate = null;
    private TextView mTextViewSearch = null;
    private ListView mListView = null;


    private List<ListData> mData = null;
    private CustomListAdapter mAdapter = null;

    private Gson mGson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_list);

        mData = new ArrayList<>();
        mAdapter = new CustomListAdapter();

        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle("Record List");
        setSupportActionBar(mToolbar);

        mTextViewStartDate = findViewById(R.id.textViewStartDate);
        mTextViewEndDate = findViewById(R.id.textViewEndDate);
        mTextViewSearch = findViewById(R.id.textViewSearch);
        mListView = findViewById(R.id.listView);

        mTextViewStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subClickStartDate();
            }
        });

        mTextViewEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subClickEndDate();
            }
        });

        mTextViewSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subClickSearch();
            }
        });

        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TODO 进入详情页面 可修改和删除
                Log.i("RecordListActivity","Click Item");
            }
        });

        showBackOption();

        subInit();
    }

    private void subInit(){
        mTextViewStartDate.setText(DateTool.getDateStr("yyyy-MM-dd"));
        mTextViewEndDate.setText(DateTool.getDateStr("yyyy-MM-dd"));
    }

    private void subClickStartDate(){
        Log.i("RecordListActivity","subClickStartDate");
        CommonFun.showDatePickDlg(this, Calendar.getInstance(), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar date = Calendar.getInstance();
                date.set(year,monthOfYear,dayOfMonth,0,0,0);
                mTextViewStartDate.setText(DateTool.getDateStr(date.getTime(),"yyyy-MM-dd"));
            }
        });
    }

    private void subClickEndDate(){
        Log.i("RecordListActivity","subClickEndDate");
        CommonFun.showDatePickDlg(this, Calendar.getInstance(), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar date = Calendar.getInstance();
                date.set(year,monthOfYear,dayOfMonth,0,0,0);
                mTextViewEndDate.setText(DateTool.getDateStr(date.getTime(),"yyyy-MM-dd"));
            }
        });
    }

    private boolean searchCheck(){
        Log.i("msg","searchCheck");
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();

        try {
            startDate.setTime(DateTool.getDateFromStr(mTextViewStartDate.getText().toString(),"yyyy-MM-dd"));
            endDate.setTime(DateTool.getDateFromStr(mTextViewEndDate.getText().toString(),"yyyy-MM-dd"));
        } catch (ParseException e) {
            CommonFun.showError(RecordListActivity.this,"日期格式转换错误",false);
            e.printStackTrace();
            return false;
        }
        if(startDate.after(endDate)){
            String strT = mTextViewStartDate.getText().toString();
            mTextViewStartDate.setText(mTextViewEndDate.getText());
            mTextViewEndDate.setText(strT);
        }
        return true;
    }

    private void subClickSearch(){
        Log.i("RecordListActivity","subClickSearch");

        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();



        try {
            startDate.setTime(DateTool.getDateFromStr(mTextViewStartDate.getText().toString(),"yyyy-MM-dd"));
            endDate.setTime(DateTool.getDateFromStr(mTextViewEndDate.getText().toString(),"yyyy-MM-dd"));
        } catch (ParseException e) {
            CommonFun.showError(RecordListActivity.this,"日期格式转换错误",false);
            e.printStackTrace();
            return;
        }

        mTextViewSearch.setClickable(false);
        if(searchCheck()){
            String msg = mTextViewStartDate.getText().toString();
            msg = msg + "\n" + mTextViewEndDate.getText().toString();
            CommonFun.showMsg(this,msg);


            List<Map<String,String>> recordList = mGson.fromJson(DataHandler.getRecordList(startDate,endDate), new TypeToken<List<Map<String,String>>>(){}.getType());

            mData.clear();
            mAdapter.notifyDataSetChanged();

            for(Map<String,String> data : recordList){
                ListData item = new ListData();
                item.mDate = data.get("date");
                item.mMoney = data.get("money");
                item.mCategory = data.get("categoryName");
                item.mRemark = data.get("remark");
                mData.add(item);
            }
            Log.i("msg",String.valueOf(mData.size()));
            if(mData.size() > 0){
                mAdapter.notifyDataSetChanged();
            }
        }
        mTextViewSearch.setClickable(true);
    }


    private class ListData implements Comparable<ListData>{

        private String mDate = "";
        private String mMoney = "";
        private String mCategory = "";
        private String mRemark = "";
        private String mAddTime = "";

        @Override
        public int compareTo(@NonNull ListData o) {
            if(mDate.equals(o.mDate)){
                return mAddTime.compareTo(o.mAddTime);
            }
            else{
                //                return mDate.compareTo(o.mDate);
                return o.mDate.compareTo(mDate);
            }
        }
    }

    static class ViewHolder{
        public TextView mTvRemarkOrCategory = null;
        public TextView mTvCategoryOrNull = null;
        public TextView mTvDate = null;
        public TextView mTvMoney = null;
    }

    private class CustomListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            LayoutInflater inflater = getLayoutInflater();

            if(convertView == null){
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.activity_record_list_item,parent,false);
                holder.mTvRemarkOrCategory = convertView.findViewById(R.id.textView_RemarkOrCategory);
                holder.mTvCategoryOrNull = convertView.findViewById(R.id.textView_CategoryOrNull);
                holder.mTvDate = convertView.findViewById(R.id.textView_Date);
                holder.mTvMoney = convertView.findViewById(R.id.textView_Money);
            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }

            ListData ld = mData.get(position);
            if(ld.mRemark.length() > 0){
                holder.mTvRemarkOrCategory.setText(ld.mRemark);
                holder.mTvCategoryOrNull.setText(ld.mCategory);
                holder.mTvCategoryOrNull.setVisibility(TextView.VISIBLE);
            }
            else{
                holder.mTvRemarkOrCategory.setText(ld.mCategory);
                holder.mTvCategoryOrNull.setVisibility(TextView.GONE);
            }
            holder.mTvDate.setText(ld.mDate);
            holder.mTvMoney.setText(ld.mMoney);

            convertView.setTag(holder);

            return convertView;
        }
    }
}


    /**


     import com.google.gson.reflect.TypeToken;

     import java.lang.reflect.Type;
     import java.text.SimpleDateFormat;
     import java.util.ArrayList;
     import java.util.Calendar;
     import java.util.Collections;
     import java.util.List;
     import java.util.Locale;
     import java.util.Map;

     public class RecordListActivity extends BaseActivity {

     private Calendar mCalendarStart = null;
     private Calendar mCalendarEnd = null;
     private List<ListData> mData = null;

     private ListView mListView = null;
     private CustomListAdapter mAdapter = null;

     private Button mBtnStart = null;
     private Button mBtnEnd = null;
     private TextView mBtnSearch = null;

     private static SimpleDateFormat mSimpleDateFormat =
     new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

     @Override
     protected void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     CustomTitleBar.setStatusBarColorWhite(RecordListActivity.this);
     setContentView(R.layout.activity_record_list);

     setActionBarLeftTitle("Record List");
     setActionBarRightTitle("");
     setShowActionBarBackBtn();
     addClickListener();

     pageInit();
     refreshListData();
     }

     private void pageInit(){
     mCalendarStart = Calendar.getInstance();
     mCalendarStart.set(mCalendarStart.get(Calendar.YEAR),
     mCalendarStart.get(Calendar.MONTH),
     mCalendarStart.get(Calendar.DAY_OF_MONTH),
     0,0,0);
     mCalendarStart.add(Calendar.DAY_OF_MONTH,-7);
     mCalendarEnd = Calendar.getInstance();
     mCalendarEnd.set(mCalendarEnd.get(Calendar.YEAR),
     mCalendarEnd.get(Calendar.MONTH),
     mCalendarEnd.get(Calendar.DAY_OF_MONTH),
     0,0,0);

     mData = new ArrayList<ListData>();

     mListView = findViewById(R.id.listView_record);
     mAdapter = new CustomListAdapter();
     mListView.setAdapter(mAdapter);
     mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
     @Override
     public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
     //TODO 进入详情页面 可修改和删除
     }
     });

     mBtnStart = findViewById(R.id.btn_left_date);
     mBtnStart.setText(mSimpleDateFormat.format(mCalendarStart.getTime()));
     mBtnStart.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {
     CommonFun.showDatePickDlg(RecordListActivity.this,
     mCalendarStart,
     new DatePicker.OnDateChangedListener() {
     @Override
     public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
     mCalendarStart.set(year,monthOfYear,dayOfMonth);
     mBtnStart.setText(mSimpleDateFormat.format(mCalendarStart.getTime()));
     }
     });
     }
     });
     mBtnEnd = findViewById(R.id.btn_right_date);
     mBtnEnd.setText(mSimpleDateFormat.format(mCalendarEnd.getTime()));
     mBtnEnd.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {
     CommonFun.showDatePickDlg(RecordListActivity.this,
     mCalendarEnd,
     new DatePicker.OnDateChangedListener() {
     @Override
     public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
     mCalendarEnd.set(year, monthOfYear, dayOfMonth);
     mBtnEnd.setText(mSimpleDateFormat.format(mCalendarEnd.getTime()));
     }
     });
     }
     });

     mBtnSearch = findViewById(R.id.btn_search);
     mBtnSearch.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {
     if(mCalendarEnd.before(mCalendarStart)){
     CommonFun.showMsg(RecordListActivity.this,"结束日期不能早于开始日期");
     }
     else{
     refreshListData();
     }
     }
     });
     }

     private void refreshListData(){
     Log.i("TODO","---------------------------------------");
     Log.i("TODO","刷新列表数据");
     Log.i("TODO",
     "StartDate - " + mSimpleDateFormat.format(mCalendarStart.getTime())
     + " | " + "EndDate - " + mSimpleDateFormat.format(mCalendarEnd.getTime())
     );

     mData.clear();
     mAdapter.notifyDataSetChanged();

     FormRecordSearch formRS = new FormRecordSearch();
     formRS.startDate = mSimpleDateFormat.format(mCalendarStart.getTime());
     formRS.endDate = mSimpleDateFormat.format(mCalendarEnd.getTime());

     NetWorkImpl n = new NetWorkImpl();
     String data;
     try{
     data = n.requestFormat(formRS);
     }catch (Exception ex){
     CommonFun.showError(RecordListActivity.this,ex.getMessage(),false);
     return;
     }

     Log.i("data",data);
     if(data.equals("")){
     return;
     }

     String address = Global.WEB_ADDRESS + "/Records/search";

     HttpHandler httpHandler = new HttpHandler();
     httpHandler.getHttpData(address,"get",data,new HttpHandler.OnGetHttpDataListener(){

     @Override
     public void onGetHttpData(String data) {
     Log.i("data",data);
     NetWorkImpl n = new NetWorkImpl();
     Type mType = new TypeToken<List<ListData>>() {
     }.getType();
     List<ListData> list;
     try{
     list = n.responseFormat(data,mType);
     }catch (Exception ex){
     CommonFun.showError(RecordListActivity.this,ex.getMessage(),false);
     return;
     }

     if(list == null){
     return;
     }
     Collections.sort(list);
     mData = list;
     mAdapter.notifyDataSetChanged();
     }

     @Override
     public void onGetError(Exception ex) {
     ex.printStackTrace();
     CommonFun.showError(RecordListActivity.this,ex.getMessage(),false);
     }

     @Override
     public void onPreExecute() {
     mBtnSearch.setEnabled(false);
     }

     @Override
     public void onPostExecute() {
     mBtnSearch.setEnabled(true);
     }
     });




     //        mData.add(new ListData("2010-01-01",
     //                "123456.00",
     //                "Category | Category | Category | Category",
     //                "刷新列表数据刷新列表数据刷新列表数据"));
     //        mData.add(new ListData("2010-01-01",
     //                "123456.00",
     //                "Category | Category | Category | Category",
     //                ""));
     //
     //        int l = Calendar.getInstance().get(Calendar.SECOND);
     //        for(int i=0;i<l;i++){
     //            mData.add(new ListData("2000-01-01",
     //                    "-123456.00",
     //                    "Category - " + String.valueOf(i),
     //                    "Remark - " + String.valueOf(i)));
     //        }

     }

     static class ViewHolder{
     public TextView mTvRemarkOrCategory;
     public TextView mTvCategoryOrNull;
     public TextView mTvDate;
     public TextView mTvMoney;
     }

     private class CustomListAdapter extends BaseAdapter {

     @Override
     public int getCount() {
     return mData.size();
     }

     @Override
     public Object getItem(int position) {
     return mData.get(position);
     }

     @Override
     public long getItemId(int position) {
     return position;
     }

     @Override
     public View getView(int position, View convertView, ViewGroup parent) {
     ViewHolder holder = null;
     LayoutInflater inflater = getLayoutInflater();

     if(convertView == null){
     holder = new ViewHolder();
     convertView = inflater.inflate(R.layout.activity_record_list_item,
     parent,false);
     holder.mTvRemarkOrCategory = convertView.findViewById(R.id.textView_RemarkOrCategory);
     holder.mTvCategoryOrNull = convertView.findViewById(R.id.textView_CategoryOrNull);
     holder.mTvDate = convertView.findViewById(R.id.textView_Date);
     holder.mTvMoney = convertView.findViewById(R.id.textView_Money);
     }
     else{
     holder = (ViewHolder)convertView.getTag();
     }

     ListData ld = mData.get(position);
     if(ld.mRemark.length() > 0){
     holder.mTvRemarkOrCategory.setText(ld.mRemark);
     holder.mTvCategoryOrNull.setText(ld.mCategory);
     holder.mTvCategoryOrNull.setVisibility(TextView.VISIBLE);
     }
     else{
     holder.mTvRemarkOrCategory.setText(ld.mCategory);
     holder.mTvCategoryOrNull.setVisibility(TextView.GONE);
     }
     holder.mTvDate.setText(ld.mDate);
     holder.mTvMoney.setText(ld.mMoney);

     convertView.setTag(holder);
     return convertView;
     }
     }

     private class FormRecordSearch{
     String startDate;
     String endDate;
     }

     private class ListData implements Comparable<ListData>{

     private String mDate = "";
     private String mMoney = "";
     private String mCategory = "";
     private String mRemark = "";
     private String mAddTime = "";

     @Override
     public int compareTo(@NonNull ListData o) {
     if(mDate.equals(o.mDate)){
     return mAddTime.compareTo(o.mAddTime);
     }
     else{
     //                return mDate.compareTo(o.mDate);
     return o.mDate.compareTo(mDate);
     }
     }
     }
     }


     **/

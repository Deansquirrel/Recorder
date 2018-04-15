package com.yuansong.recorder.Dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.yuansong.recorder.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import java.text.Collator;

/**
 * Created by yuansong on 2018/2/4.
 */

public class ListPickDlg extends AppCompatDialogFragment {

    private String mTitle = "";
    private String mCurrKey = null;
    private List<ListData> mData = new ArrayList<ListData>();
    private OnDataSelectListener mBack = null;

    public interface OnDataSelectListener {
        void onDataSelect(String key, String value);
    }

    public void setTitle(String title){
        mTitle = title;
    }

    public void setData(Map<String,String> data){
        mData.clear();
        for (String key:data.keySet()) {
            if(key.equals(mCurrKey)){
                mData.add(new ListData(key,data.get(key),true));
            }
            else{
                mData.add(new ListData(key,data.get(key)));
            }
        }
        Collections.sort(mData);
    }

    public void setCurrKey(String key){
        mCurrKey = key;
    }

    public void setOnDataSelectListener(OnDataSelectListener listener){
        mBack = listener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_picker_list, container);
        TextView tv = view.findViewById(R.id.dialog_picker_list_textView);
        tv.setText(mTitle);
        final ListView lv = view.findViewById(R.id.dialog_picker_list_listView);
        lv.setAdapter(new CustomListAdapter());

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = view.findViewById(R.id.dialog_pick_list_item_textView_Key);
                mCurrKey = tv.getText().toString();
                for(ListData ld : mData){
                    if(ld.getKey().equals(mCurrKey)){
                        if(mBack != null){
                            mBack.onDataSelect(ld.getKey(),ld.getValue());
                        }
                    }
                }
                ListPickDlg.this.dismiss();
            }
        });

        return view;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }

    //内部列表数据子类
    private class ListData implements Comparable<ListData>{
        private String mKey = "";
        private String mValue = "";
        private boolean mIsSelected = false;

        private ListData(String key,String value){
            mKey = key;
            mValue = value;
            mIsSelected = false;
        }

        private ListData(String key,String value,boolean isSelected){
            mKey = key;
            mValue = value;
            mIsSelected = isSelected;
        }

        private String getKey(){
            return mKey;
        }

        private String getValue(){
            return mValue;
        }

        private boolean isSelected(){
            return mIsSelected;
        }

        @Override
        public int compareTo(@NonNull ListData ld) {
            Collator instance = Collator.getInstance(Locale.CHINA);
            return instance.compare(this.getValue(), ld.getValue());
        }
    }

    static class ViewHolder{
        public TextView tv_key;
        public TextView tv_value;
    }

    private class CustomListAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int position) {
            return mData.get(position);
//            return null;
        }


        @Override
        public long getItemId(int position) {
            return position;
//            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            LayoutInflater inflater = getLayoutInflater();
            if(convertView == null){
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.dialog_picker_list_item,
                        parent,
                        false);
                holder.tv_key = convertView.findViewById(R.id.dialog_pick_list_item_textView_Key);
                holder.tv_value = convertView.findViewById(R.id.dialog_pick_list_item_textView_Value);
            }
            else{
                holder = (ViewHolder)convertView.getTag();
            }

            ListData ld = mData.get(position);
            holder.tv_key.setText(ld.getKey());
            holder.tv_value.setText(ld.getValue());
            if(ld.isSelected()){
                holder.tv_value.setSelected(true);
            }
            else{
                holder.tv_value.setSelected(false);
            }

            convertView.setTag(holder);
            return convertView;
        }
    }
}

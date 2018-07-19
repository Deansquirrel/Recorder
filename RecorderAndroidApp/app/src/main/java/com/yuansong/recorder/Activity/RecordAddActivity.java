package com.yuansong.recorder.Activity;

import android.support.v7.widget.Toolbar;

import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yuansong.common.DateTool;
import com.yuansong.recorder.Common.CommonFun;
import com.yuansong.recorder.Dialog.ListPickDlg;
import com.yuansong.recorder.Http.DataHandler;
import com.yuansong.recorder.Inputfilter.DecimalDigitsInputFilter;
import com.yuansong.recorder.R;

import java.util.Calendar;
import java.util.Map;

public class RecordAddActivity extends BaseActivity {

    private static final int CATEGORY_TYPE_SHR = 0;
    private static final int CATEGORY_TYPE_ZHC = 1;

    private Toolbar mToolbar = null;
    private TextView mTextViewDate = null;
    private EditText mEditTextMoney = null;
    private RadioGroup mRadioGroupCategory = null;
    private RadioButton mRadioButtonShr = null;
    private RadioButton mRadioButtonZhc = null;
    private TextView  mTextViewCategory = null;
    private int mRecordCategoryId = -1;
    private EditText mEditTextRemark = null;
    private Button mBtnSubmit = null;
    private Button mBtnReset = null;

    private Gson mGson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_add);

        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle("Record Add");
        setSupportActionBar(mToolbar);

        mTextViewDate = findViewById(R.id.textViewDate);

        mEditTextMoney = findViewById(R.id.editTextMoney);
        mRadioGroupCategory = findViewById(R.id.radioGroup);
        mRadioButtonShr = findViewById(R.id.radioButtonShr);
        mRadioButtonZhc = findViewById(R.id.radioButtonZhc);
        mTextViewCategory = findViewById(R.id.textViewCategory);
        mEditTextRemark = findViewById(R.id.editTextRemark);
        mBtnSubmit = findViewById(R.id.btnSubmit);
        mBtnReset = findViewById(R.id.btnReset);

        mRadioButtonZhc.setChecked(true);
        mBtnSubmit.setText("确 定");
        mBtnReset.setText("清 空");

        showBackOption();

        updateRecordCategory(-1,"");

        CommonFun.setFocus(this,mEditTextMoney);

        mTextViewDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subClickDate();
            }
        });
        mEditTextMoney.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(2)});
        mRadioGroupCategory.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                updateRecordCategory(-1,"");
            }
        });
        mTextViewCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subClickCategory();
            }
        });
        mBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subBtnClickSubmit();
            }
        });
        mBtnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subBtnClickReset();
            }
        });

        subInit();
    }

    private void updateRecordCategory(int categoryId,String categoryName){
        Log.i("msg","updateRecordCategory");
        mRecordCategoryId = categoryId;
        mTextViewCategory.setText(categoryName);
    }

    private void subClickDate(){
        Log.i("msg","subClickDate");
        CommonFun.showDatePickDlg(this, Calendar.getInstance(), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar mCalendar = Calendar.getInstance();
                mCalendar.set(year,monthOfYear,dayOfMonth,0,0,0);
                mTextViewDate.setText(DateTool.getDateStr("yyyy-MM-dd"));
            }
        });
    }

    private void subClickCategory(){
        Log.i("msg","subClickCategory");
        int categoryType;
        if(mRadioButtonZhc.isChecked()){
            categoryType = CATEGORY_TYPE_ZHC;
        }
        else if(mRadioButtonShr.isChecked()){
            categoryType = CATEGORY_TYPE_SHR;
        }
        else{
            categoryType = CATEGORY_TYPE_ZHC;
            mRadioButtonZhc.setChecked(true);
        }

        Map<String,String> categoryList = mGson.fromJson(DataHandler.getCategoryList(categoryType), new TypeToken<Map<String,String>>(){}.getType());
        CommonFun.showListPickDlg(this, "选择分类", categoryList, "", new ListPickDlg.OnDataSelectListener() {
            @Override
            public void onDataSelect(String key, String value) {
                updateRecordCategory(Integer.valueOf(key),value);
            }
        });
    }

    private void subBtnClickSubmit(){
        Log.i("msg","subBtnClickSubmit");

        mBtnSubmit.setEnabled(false);
        mBtnReset.setEnabled(false);

        Log.i("Date",mTextViewDate.getText().toString());
        Log.i("Money",mEditTextMoney.getText().toString());
        Log.i("CategoryId",String.valueOf(mRecordCategoryId));
        Log.i("CategoryName",mTextViewCategory.getText().toString());
        Log.i("Remark",mEditTextRemark.getText().toString());

        if(subSubmitCheck()){
            String msg = mTextViewDate.getText().toString();
            msg = msg + "\n" + mEditTextMoney.getText().toString();
            msg = msg + "\n" + String.valueOf(mRecordCategoryId);
            msg = msg + "\n" + mTextViewCategory.getText().toString();
            msg = msg + "\n" + mEditTextRemark.getText().toString();
            CommonFun.showMsg(this,msg);
            subInit();
        }

        mBtnSubmit.setEnabled(true);
        mBtnReset.setEnabled(true);
    }

    private void subBtnClickReset(){
        Log.i("msg","subBtnClickReset");

        mBtnSubmit.setEnabled(false);
        mBtnReset.setEnabled(false);

        subInit();
        CommonFun.setFocus(this,mEditTextMoney);

        mBtnSubmit.setEnabled(false);
        mBtnReset.setEnabled(false);
    }

    private void subInit(){
        Log.i("msg","subInit");
        mTextViewDate.setText(DateTool.getDateStr("yyyy-MM-dd"));
        mEditTextMoney.setText("");
        mRadioButtonZhc.setChecked(true);
        updateRecordCategory(-1,"");
        mEditTextRemark.setText("");
    }

    private boolean subSubmitCheck(){
        Log.i("msg","subSubmitCheck");

        String money = mEditTextMoney.getText().toString();
        if(money.trim().equals("")){
            CommonFun.showMsg(RecordAddActivity.this,"余额不能为空");
            CommonFun.setFocus(RecordAddActivity.this,mEditTextMoney);
            return false;
        }

        if(mRecordCategoryId < 0) {
            CommonFun.showMsg(RecordAddActivity.this, "请选择所属分类");
            return false;
        }
        return true;
    }

}

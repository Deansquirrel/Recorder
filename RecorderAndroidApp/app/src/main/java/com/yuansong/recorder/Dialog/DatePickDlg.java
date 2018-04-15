package com.yuansong.recorder.Dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import java.util.Calendar;

import com.yuansong.recorder.R;

/**
 * Created by yuansong on 2018/2/3.
 */

public class DatePickDlg extends AppCompatDialogFragment {

    private Calendar mCalendar = null;
    private DatePicker.OnDateChangedListener mBack = null;

    public void setCurrCalendar(Calendar calendar){
        mCalendar = calendar;
    }

    public void setOnDateChangedListener(DatePicker.OnDateChangedListener listener){
        mBack = listener;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_picker_date,container);
        DatePicker dp = view.findViewById(R.id.dialog_picker_date_datePicker);

        if(mCalendar == null){
            mCalendar = Calendar.getInstance();
        }

        dp.init(mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                if(mBack != null){
                    mBack.onDateChanged(datePicker, year, monthOfYear, dayOfMonth);
                }
                DatePickDlg.this.dismiss();
            }
        });

        return view;
    }
}

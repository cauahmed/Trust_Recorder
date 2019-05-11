package com.example.trustrecorder;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

public class OpenIntervalActivity extends WearableActivity{

    private TextView mTextView;
    Button timeBtn;


    NumberPicker noPicker = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.open_interval_screen);
        mTextView = (TextView) findViewById(R.id.text);
        timeBtn = (Button) findViewById(R.id.timeBtn);
    }

    public void setTime(View view) {


        Calendar calendar=Calendar.getInstance();
        int hour=calendar.get(Calendar.HOUR);
        int minute=calendar.get(Calendar.MINUTE);
        int second=calendar.get(Calendar.SECOND);
        TimePickerDialog timePickerDialog;

        timePickerDialog=new TimePickerDialog(OpenIntervalActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hour, int minute) {
                timeBtn.setText(hour+" hours "+minute+" mins ");
            }
        }, hour,minute,true);
        timePickerDialog.show();
    }

    public void backButton(View view)
    {
        Intent intent = new Intent(OpenIntervalActivity.this, SelectTimeActivity.class);
        startActivity(intent);
    }
}
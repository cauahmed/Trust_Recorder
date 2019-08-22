package com.example.trustrecorder;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.example.trustrecorder.CircularSeekBar.OnCircularSeekBarChangeListener;
import android.graphics.Color;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.widget.TextView;
import com.example.trustrecorder.CircularSeekBar;
import com.example.trustrecorder.UserDataDbHelper.*;


import java.text.DecimalFormat;


public class TrustLevelActivity extends WearableActivity {
    private TextView mTextView;
    private CircularSeekBar seekBar;
    public Integer trustscore;
    public String trusttype;
    public String userid;
    public String timestamp;
    //public UserDataDbHelper mUserDataDbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trust_level);
        mTextView = (TextView) findViewById(R.id.text);
        //mUserDataDbHelper = new UserDataDbHelper(this);

        final UserDataDbHelper dbHelper = new UserDataDbHelper(this);

        final SQLiteDatabase db = dbHelper.getWritableDatabase();

        //dbHelper.onCreate(db);

        final Intent intent = getIntent();

        final Long tsLong = System.currentTimeMillis() / 1000;


        seekBar = (CircularSeekBar) findViewById(R.id.circularSeekBar);
        seekBar.setCircleColor(Color.CYAN);

        seekBar.setOnSeekBarChangeListener(new OnCircularSeekBarChangeListener() {
            @Override
            public void onProgressChanged(CircularSeekBar circularSeekBar, int progress, boolean fromUser) {
                if (circularSeekBar.getProgress() <= 25) {
                    seekBar.setCircleProgressColor(Color.RED);
                } else if ((circularSeekBar.getProgress() > 25) && (circularSeekBar.getProgress() <= 50)) {
                    seekBar.setCircleProgressColor(Color.YELLOW);
                } else if ((circularSeekBar.getProgress() > 50) && (circularSeekBar.getProgress() <= 75)) {
                    seekBar.setCircleProgressColor(Color.BLUE);
                } else if ((circularSeekBar.getProgress() > 75) && (circularSeekBar.getProgress() <= 100)) {
                    seekBar.setCircleProgressColor(Color.GREEN);
                }
            }

            @Override
            public void onStopTrackingTouch(CircularSeekBar seekBar) {

                trustscore = seekBar.getProgress();

                if (trustscore <= 25) {
                    trusttype = "low";
                } else if ((trustscore > 25) && (trustscore <= 50)) {
                    trusttype = "neutral";
                } else if ((trustscore > 50) && (trustscore <= 75)) {
                    trusttype = "moderate";
                } else if ((trustscore > 75) && (trustscore <= 100)) {
                    trusttype = "high";
                }

                userid = intent.getStringExtra(Intent.EXTRA_TEXT);

                timestamp = tsLong.toString();

                dbHelper.insertNewRecorder(db, userid, timestamp, 0, trustscore, trusttype);


            }

            @Override
            public void onStartTrackingTouch(CircularSeekBar seekBar) {

            }


        });








    }



}









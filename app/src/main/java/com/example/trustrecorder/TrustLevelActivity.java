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
    public Integer progress;
    public String trusttype;
    public String userid;
    public String trustcscore;
    public String trustlscore;
    //public UserDataDbHelper mUserDataDbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trust_level);
        mTextView = (TextView) findViewById(R.id.text);

        final UserDataDbHelper dbHelper = new UserDataDbHelper(this);

        final Intent intent = getIntent();


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


                progress = seekBar.getProgress();

                if (progress <= 25) {
                    trusttype = "low";
                } else if ((progress > 25) && (progress <= 50)) {
                    trusttype = "neutral";
                } else if ((progress > 50) && (progress <= 75)) {
                    trusttype = "moderate";
                } else if ((progress > 75) && (progress <= 100)) {
                    trusttype = "high";
                }

                trustlscore = progress.toString();

                trustcscore = "0";

                userid = intent.getStringExtra(Intent.EXTRA_TEXT);

                SQLiteDatabase db = dbHelper.getWritableDatabase();

                dbHelper.insertNewRecorder(db, userid, trustcscore, trustlscore, trusttype);


            }

            @Override
            public void onStartTrackingTouch(CircularSeekBar seekBar) {

            }


        });








    }



}









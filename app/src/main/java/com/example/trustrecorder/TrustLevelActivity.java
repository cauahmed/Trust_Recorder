package com.example.trustrecorder;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;


import android.graphics.Color;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.widget.TextView;

import com.akaita.android.circularseekbar.CircularSeekBar;

import java.text.DecimalFormat;


public class TrustLevelActivity extends WearableActivity {
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trust_level);

        mTextView = (TextView) findViewById(R.id.text);
        CircularSeekBar seekBar = (CircularSeekBar) findViewById(R.id.seekbar);
        seekBar.setProgressTextFormat(new DecimalFormat("###,###,###,##0.00"));
        seekBar.setProgress(0);
        seekBar.setProgressText("Start");
        seekBar.setRingColor(Color.GREEN);
        seekBar.setOnCenterClickedListener(new CircularSeekBar.OnCenterClickedListener() {
            @Override
            public void onCenterClicked(CircularSeekBar seekBar, float progress) {
                Snackbar.make(seekBar, "Reset", Snackbar.LENGTH_SHORT).show();
                seekBar.setProgress(0);
            }
        });
        seekBar.setOnCircularSeekBarChangeListener(new CircularSeekBar.OnCircularSeekBarChangeListener() {
            @Override
            public void onProgressChanged(CircularSeekBar seekBar, float progress, boolean fromUser) {
                if (progress<5){
                    seekBar.setRingColor(Color.YELLOW);
                    seekBar.setProgressText("Very Low");
                }else if (progress<10){
                    seekBar.setRingColor(Color.CYAN);
                    seekBar.setProgressText("Low");
                }else if (progress<20){
                    seekBar.setRingColor(Color.GREEN);
                    seekBar.setProgressText("Medium");
                }else if (progress<40){
                    seekBar.setRingColor(Color.RED);
                    seekBar.setProgressText("High");
                }else {
                    seekBar.setRingColor(Color.MAGENTA);
                    seekBar.setProgressText("Very High");
                }
            }

            @Override
            public void onStartTrackingTouch(CircularSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(CircularSeekBar seekBar) {
                //Intent intent = new Intent(TrustLevelActivity.this, MainActivity.class);

                //startActivity(intent);
            }
        });



    }

}

package com.example.trustrecorder;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.os.Environment;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class SettingsActivity extends WearableActivity {

    public static final String EXTRA_TEXT = ".com.example.application.Trust_Recorder.EXTRA_TEXT";
    public static final String EXTRA_BOOLEAN = ".com.example.application.Trust_Recorder.EXTRA_BOOLEAN";

    private TextView mTextView;

    public static final String SHARED_PREFS2 = "sharedPrefs";
    public static final String BUTTON1 = "text";
    public static final String SWITCH1 = "switch1";
    private Switch switch1;
    private String switchpos;

    private static final String SAMPLE_DB_NAME = "UserData.db";
    private static final String SAMPLE_TABLE_NAME = "ChangeData";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_settings);

        mTextView = (TextView) findViewById(R.id.text);

        switch1 = (Switch) findViewById(R.id.switch1);

        // Enables Always-on*/
        setAmbientEnabled();

        loadData();
        //updateViews();

    }


    public void confirm(View view)
    {
        Switch switch1 = (Switch) findViewById(R.id.switch1);
        String position = Boolean.toString(switch1.isChecked());
        Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
        intent.putExtra(EXTRA_BOOLEAN, position);

        startActivity(intent);
        saveData();

    }


    public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS2, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SWITCH1, (Boolean.toString(switch1.isChecked())));
        editor.apply();

        Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();


    }

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS2, MODE_PRIVATE);
        switchpos = sharedPreferences.getString(SWITCH1, "");
        if (switchpos.compareTo("true")==0){
            switch1.setChecked(true);
        }else {
            switch1.setChecked(false);
        }

    }


}

package com.example.trustrecorder;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
//import ;

import com.google.android.gms.wearable.Wearable;

import java.sql.Time;
import java.util.Date;

import static com.example.trustrecorder.SettingsActivity.EXTRA_BOOLEAN;

public class MainActivity extends WearableActivity {




    private TextView mTextView;
    private TextView auto_id;

    //Spinner spn1;
    TextView user;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";
    public static final String SPINPOS = ".com.example.application.Trust_Recorder.SPINPOS";
    public Boolean Switch = false;

    private String usertext;
    private int spinnum;
    String position;
    String auto_id_num;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();

        auto_id = findViewById(R.id.test_id);
        //generate test id by time stamp;

        auto_id_num = Long.toString(System.currentTimeMillis() / 1000);



        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        String t = format.format(new Date());
        auto_id.setText(t);


        if (intent.getExtras() == null) {
            position = "Input Mode";

        } else {
            position = intent.getStringExtra(EXTRA_BOOLEAN);
        }

        mTextView = (TextView) findViewById(R.id.text);


        if (position.compareTo("false") == 0) {
        } else if (position.compareTo("true") == 0) {
            Switch = true;
        }

        setAmbientEnabled();

    }

    public void gotosetting (View view)
    {
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(intent);
        saveData();

    }

    public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(TEXT, auto_id_num);
        editor.apply();

        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();

    }

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        usertext = sharedPreferences.getString(TEXT, "");
    }

    public void updateViews(){
        user.setText(usertext);
    }


    public void initiate (View view) {
        if (Switch.equals(false)) {
            Intent intent = new Intent(MainActivity.this, TrustChangeActivity.class);
            intent.putExtra(Intent.EXTRA_TEXT, auto_id_num);
            intent.putExtra(SPINPOS, position);
            startActivity(intent);
            Toast.makeText(this, "Trust Change", Toast.LENGTH_SHORT).show();

        }else{
            Intent intent = new Intent(MainActivity.this, TrustLevelActivity.class);
            intent.putExtra(Intent.EXTRA_TEXT, auto_id_num);
            intent.putExtra(SPINPOS, position);
            startActivity(intent);
            Toast.makeText(this, "Trust Level", Toast.LENGTH_SHORT).show();

        }
    }





}



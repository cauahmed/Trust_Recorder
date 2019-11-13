package com.example.trustrecorder;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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


    //Spinner spn1;
    TextView user;
    TextView test_id;


    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";
    public static final String SPINPOS = ".com.example.application.Trust_Recorder.SPINPOS";
    public Boolean Switch = false;

    private String usertext;
    String test_id_val;
    String position;
    String auto_id_num;


    final String[] Options = {"test_user_1", "test_user_2", "test_user_3"};
    AlertDialog.Builder window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();

        test_id = (TextView) findViewById(R.id.test_id);

        //generate test id by time stamp
        auto_id_num = Long.toString(System.currentTimeMillis() / 1000);

        if (intent.getExtras() == null) {
            position = "Input Mode";

        } else {
            position = intent.getStringExtra(EXTRA_BOOLEAN);
        }


        if (position.compareTo("false") == 0) {
        } else if (position.compareTo("true") == 0) {
            Switch = true;
        }

        setAmbientEnabled();

    }

    public void selectUser (View view){
        window = new AlertDialog.Builder(this);
        window.setTitle("Select a user");
        window.setItems(Options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which == 0){
                    test_id.setText("Test User 1");
                    test_id_val = "test_user_1";

                }else if(which == 1){
                    test_id.setText("Test User 2");
                    test_id_val = "test_user_2";

                }else if(which == 2){
                    test_id.setText("Test User 3");
                    test_id_val = "test_user_3";

                }else{
                    Toast.makeText(getApplicationContext(), "No selection due to error", Toast.LENGTH_LONG).show();
                }
            }
        });

        window.show();
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
            intent.putExtra(Intent.EXTRA_TEXT, test_id_val);
            intent.putExtra(SPINPOS, position);
            startActivity(intent);
            Toast.makeText(this, "Trust Change", Toast.LENGTH_SHORT).show();

        }else{
            Intent intent = new Intent(MainActivity.this, TrustLevelActivity.class);
            intent.putExtra(Intent.EXTRA_TEXT, test_id_val);
            intent.putExtra(SPINPOS, position);
            startActivity(intent);
            Toast.makeText(this, "Trust Level", Toast.LENGTH_SHORT).show();

        }
    }





}



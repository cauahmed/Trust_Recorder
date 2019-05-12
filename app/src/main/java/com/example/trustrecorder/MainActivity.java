package com.example.trustrecorder;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends WearableActivity {
    public static final String EXTRA_TEXT = ".com.example.application.Trust_Recorder.EXTRA_TEXT";
    public static final String EXTRA_NUMBER = ".com.example.application.Trust_Recorder.EXTRA_NUMBER";

    private TextView mTextView;
    Spinner spn1;
    TextView user;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";
    public static final String INTEGER = "int";

    private String usertext;
    private int spinnum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.text);
        spn1 = (Spinner) findViewById(R.id.spn1);
        user = (TextView) findViewById(R.id.user);



        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.users, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spn1.setAdapter(adapter);

        // Enables Always-on
        setAmbientEnabled();

        spn1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                switch(position){
                    case 0:
                        user.setText("Welcome");
                        break;
                    case 1:
                        user.setText("Welcome Asif");
                        break;
                    case 2:
                        user.setText("Welcome Nadim");
                        break;
                    case 3:
                        user.setText("Welcome Sang");
                        break;
                    case 4:
                        user.setText("Welcome Apu");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        }
        );
        loadData();
        updateViews();

    }

    public void gotosetting (View view)
    {
        TextView user = (TextView) findViewById(R.id.user);
        String text = user.getText().toString();
        Spinner spn1 = (Spinner) findViewById(R.id.spn1) ;
        int pos = spn1.getSelectedItemPosition();

        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        intent.putExtra(EXTRA_TEXT, text);
        intent.putExtra(EXTRA_NUMBER, pos);
        startActivity(intent);
        saveData();

    }

    public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(INTEGER, spn1.getSelectedItemPosition());
        editor.putString(TEXT, user.getText().toString());
        editor.apply();

        Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();

    }

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        spinnum = sharedPreferences.getInt(INTEGER, 0);
        usertext = sharedPreferences.getString(TEXT, "");
    }

    public void updateViews(){
        user.setText(usertext);
        spn1.setSelection(spinnum);
    }



}



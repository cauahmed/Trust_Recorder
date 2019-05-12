package com.example.trustrecorder;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class SettingsActivity extends WearableActivity {

    private TextView mTextView;
    Spinner spn2;
    Spinner spn3;
    Button timeBtn;
    public static final String SHARED_PREFS2 = "sharedPrefs";
    public static final String INTEGER2 = "int";
    public static final String INTEGER3 = "int";
    public static final String BUTTON1 = "text";

    private int spinnum2;
    private int spinnum3;
    private String btntext1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_settings);

        mTextView = (TextView) findViewById(R.id.text);
        spn2 = (Spinner) findViewById(R.id.spn2);
        spn3 = (Spinner) findViewById(R.id.spn3);
        timeBtn = (Button) findViewById(R.id.timeBtn);


        //Spinner spinner = (Spinner) findViewById(R.id.spn2);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.input, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spn2.setAdapter(adapter);

        // Enables Always-on
        setAmbientEnabled();

        //Spinner spinner = (Spinner) findViewById(R.id.spn3);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.interval, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spn3.setAdapter(adapter2);

        // Enables Always-on
        setAmbientEnabled();
        loadData();
        updateViews();

    }

    public void setTime(View view) {


        Calendar calendar=Calendar.getInstance();
        int hour=calendar.get(Calendar.HOUR);
        int minute=calendar.get(Calendar.MINUTE);
        int second=calendar.get(Calendar.SECOND);
        TimePickerDialog timePickerDialog;

        timePickerDialog=new TimePickerDialog(SettingsActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hour, int minute) {
                timeBtn.setText(hour+" hours "+minute+" mins ");
            }
        }, hour,minute,true);
        timePickerDialog.show();
    }

    public void confirm(View view)
    {
        Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
        startActivity(intent);
        saveData();

    }


    public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS2, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(INTEGER2, spn2.getSelectedItemPosition());
        editor.putInt(INTEGER3, spn3.getSelectedItemPosition());
        editor.putString(BUTTON1, timeBtn.getText().toString());
        editor.apply();

        Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();


    }

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS2, MODE_PRIVATE);
        spinnum2 = sharedPreferences.getInt(INTEGER2, 0);
        spinnum3 = sharedPreferences.getInt(INTEGER3, 0);
        btntext1 = sharedPreferences.getString(BUTTON1, "");

    }

    public void updateViews(){
        spn2.setSelection(spinnum2);
        spn3.setSelection(spinnum3);
        timeBtn.setText(btntext1);
    }
}

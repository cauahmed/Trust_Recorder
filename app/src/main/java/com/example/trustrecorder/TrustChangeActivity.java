package com.example.trustrecorder;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Handler;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;


import android.graphics.Color;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.widget.TextView;
import android.widget.Toast;


public class TrustChangeActivity extends WearableActivity {
    private TextView mTextView;
    public AlphaAnimation buttonClick = new AlphaAnimation(0.2F, 1F);
    int i=0;
    public String userid;
    public String trustcscore;
    public String trustlscore;
    public String trusttype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trust_change);
        final Intent intent = getIntent();
        final UserDataDbHelper dbHelper = new UserDataDbHelper(this);


        mTextView = (TextView) findViewById(R.id.text);
        Button button1 = (Button) findViewById(R.id.button_top);
        Button button2 = (Button) findViewById(R.id.button_middle);
        Button button3 = (Button) findViewById(R.id.button_bottom);

        addClickEffect(button1);
        addClickEffect(button2);
        addClickEffect(button3);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (i == 1) {
                            Toast.makeText(TrustChangeActivity.this, "UP", Toast.LENGTH_SHORT).show();
                            trustcscore = "1";
                            trustlscore = "0";
                            userid = intent.getStringExtra(Intent.EXTRA_TEXT);
                            trusttype = "positive";
                            SQLiteDatabase db = dbHelper.getWritableDatabase();
                            dbHelper.insertNewRecorder(db, userid, trustcscore, trustlscore, trusttype);
                        } else if (i == 2){
                            Toast.makeText(TrustChangeActivity.this, "D UP", Toast.LENGTH_SHORT).show();
                        }
                        i = 0;
                    }
                }, 500);


            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (i == 1) {
                            Toast.makeText(TrustChangeActivity.this, "EQ", Toast.LENGTH_SHORT).show();
                            trustcscore = "0";
                            trustlscore = "0";
                            userid = intent.getStringExtra(Intent.EXTRA_TEXT);
                            trusttype = "unchanged";
                            SQLiteDatabase db = dbHelper.getWritableDatabase();
                            dbHelper.insertNewRecorder(db, userid, trustcscore, trustlscore, trusttype);

                        } else if (i == 2){
                            Toast.makeText(TrustChangeActivity.this, "D EQ", Toast.LENGTH_SHORT).show();
                        }
                        i = 0;
                    }
                }, 500);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (i == 1) {
                            Toast.makeText(TrustChangeActivity.this, "DN", Toast.LENGTH_SHORT).show();
                            trustcscore = "-1";
                            trustlscore = "0";
                            userid = intent.getStringExtra(Intent.EXTRA_TEXT);
                            trusttype = "negative";
                            SQLiteDatabase db = dbHelper.getWritableDatabase();
                            dbHelper.insertNewRecorder(db, userid, trustcscore, trustlscore, trusttype);
                        } else if (i == 2){
                            Toast.makeText(TrustChangeActivity.this, "D DN", Toast.LENGTH_SHORT).show();
                        }
                        i = 0;
                    }
                }, 500);
            }
        });

    }



    public void addClickEffect(View view)
    {
        Drawable drawableNormal = view.getBackground();

        Drawable drawablePressed = view.getBackground().getConstantState().newDrawable();
        drawablePressed.mutate();
        drawablePressed.setColorFilter(Color.argb(50, 0, 0, 0), PorterDuff.Mode.SRC_ATOP);

        StateListDrawable listDrawable = new StateListDrawable();
        listDrawable.addState(new int[] {android.R.attr.state_pressed}, drawablePressed);
        listDrawable.addState(new int[] {}, drawableNormal);
        view.setBackground(listDrawable);
    }
}
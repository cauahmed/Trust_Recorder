package com.example.trustrecorder;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;


import android.graphics.Color;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.akaita.android.circularseekbar.CircularSeekBar;
import com.example.trustrecorder.R;

import java.text.DecimalFormat;


public class TrustChangeActivity extends WearableActivity {
    private TextView mTextView;
    public AlphaAnimation buttonClick = new AlphaAnimation(0.2F, 1F);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trust_change);

        mTextView = (TextView) findViewById(R.id.text);
        Button button1 = (Button) findViewById(R.id.button_top);
        Button button2 = (Button) findViewById(R.id.button_middle);
        Button button3 = (Button) findViewById(R.id.button_bottom);

        addClickEffect(button1);
        addClickEffect(button2);
        addClickEffect(button3);

    }

    public void up (View view){
        Toast.makeText(this,"UPTICK",Toast.LENGTH_SHORT).show();
    }

    public void equals (View view){
        Toast.makeText(this,"NO CHANGE",Toast.LENGTH_SHORT).show();
    }

    public void down (View view){
        Toast.makeText(this,"DOWNTICK",Toast.LENGTH_SHORT).show();
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
package com.example.startup;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    SeekBar sbRed,sbGreen,sbBlue;
    SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponent();
        initEvent();
    }
    void initComponent(){
        sbRed = findViewById(R.id.sbRed);
        sbGreen = findViewById(R.id.sbGreen);
        sbBlue = findViewById(R.id.sbBlue);
        pref = getSharedPreferences("save_color", Context.MODE_PRIVATE);

        int red = pref.getInt("red",0);
        int green = pref.getInt("green",0);
        int blue = pref.getInt("blue",0);

        sbRed.setProgress(red);
        sbGreen.setProgress(green);
        sbBlue.setProgress(blue);
        color = Color.rgb(sbRed.getProgress(),sbGreen.getProgress(),sbBlue.getProgress());
        sbRed.getRootView().setBackgroundColor(color);
    }
    int color;
    void initEvent(){
        sbRed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                color = Color.rgb(sbRed.getProgress(),sbGreen.getProgress(),sbBlue.getProgress());
                sbRed.getRootView().setBackgroundColor(color);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                SharedPreferences.Editor editor = pref.edit();
                editor.putInt("red",sbRed.getProgress());
                editor.commit();
            }
        });
        sbGreen.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                color = Color.rgb(sbRed.getProgress(),sbGreen.getProgress(),sbBlue.getProgress());
                sbRed.getRootView().setBackgroundColor(color);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                SharedPreferences.Editor editor = pref.edit();
                editor.putInt("green",sbGreen.getProgress());
                editor.commit();
            }
        });
        sbBlue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                color = Color.rgb(sbRed.getProgress(),sbGreen.getProgress(),sbBlue.getProgress());
                sbRed.getRootView().setBackgroundColor(color);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                SharedPreferences.Editor editor = pref.edit();
                editor.putInt("blue",sbBlue.getProgress());
                editor.commit();
            }
        });
    }
}

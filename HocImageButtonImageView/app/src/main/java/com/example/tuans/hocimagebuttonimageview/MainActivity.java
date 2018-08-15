package com.example.tuans.hocimagebuttonimageview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {
    RadioButton radNha,radEch;
    ImageView imgHinh;
    ImageButton btnThoat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        radEch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    imgHinh.setImageResource(R.drawable.frog);
                }
            }
        });
        radNha.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    imgHinh.setImageResource(R.drawable.building);
                }
            }
        });
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void addControls() {
        radEch= (RadioButton) findViewById(R.id.radEch);
        radNha= (RadioButton) findViewById(R.id.radNha);
        imgHinh= (ImageView) findViewById(R.id.imgHinh);
        btnThoat= (ImageButton) findViewById(R.id.btnExit);
    }
}

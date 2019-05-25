package com.example.startup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Setting extends AppCompatActivity {

    RadioButton rdRed;
    RadioButton rdGreen;
    RadioButton rdBlue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        rdRed = findViewById(R.id.rdRed);
        rdGreen = findViewById(R.id.rdGreen);
        rdBlue = findViewById(R.id.rdBlue);

        RadioGroup rgChoose = findViewById(R.id.rgChoose);


        Button btSave = findViewById(R.id.btSave);

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String colorChoose = "RED";
                if (rdRed.isChecked()){
                    colorChoose = "RED";
                }
                if (rdGreen.isChecked()){
                    colorChoose = "GREEN";
                }
                if (rdBlue.isChecked()){
                    colorChoose = "BLUE";
                }
//                Intent intent = new Intent(Setting.this, MainActivity.class);
//                startActivity(intent);
                Intent intent = new Intent();
                intent.putExtra("Color", colorChoose);
                setResult(200,intent);
                finish();
            }
        });

    }
}

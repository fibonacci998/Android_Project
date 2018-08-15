package com.example.tuans.hoccheckbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    CheckBox chkAnd,chkWin,chkIos;
    Button btnChon;
    TextView txtMonHoc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnChon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyChonMonHoc();
            }
        });
    }

    private void xuLyChonMonHoc() {
        String s="";
        if (chkAnd.isChecked())
            s+=chkAnd.getText().toString()+"\n";
        if (chkWin.isChecked())
            s+=chkWin.getText().toString()+"\n";
        if (chkIos.isChecked())
            s+=chkIos.getText().toString();
        txtMonHoc.setText(s);
    }

    private void addControls() {
        chkAnd= (CheckBox) findViewById(R.id.chk1);
        chkWin= (CheckBox) findViewById(R.id.chk2);
        chkIos= (CheckBox) findViewById(R.id.chk3);
        btnChon= (Button) findViewById(R.id.btnChon);
        txtMonHoc= (TextView) findViewById(R.id.txtMonHoc);
    }
}

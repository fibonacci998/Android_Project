package com.example.tuans.hocradiobutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RadioButton radRatTuyetVoi,radTuyeVoi,radChapNhan,radKhongTot;
    Button btnBinhChon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnBinhChon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyBinhChon();
            }
        });
    }

    private void xuLyBinhChon() {
        String s="";
        if (radRatTuyetVoi.isChecked())
            s=radRatTuyetVoi.getText().toString();
        else if (radTuyeVoi.isChecked())
            s=radTuyeVoi.getText().toString();
        else if (radChapNhan.isChecked())
            s=radChapNhan.getText().toString();
        else if (radKhongTot.isChecked())
            s=radKhongTot.getText().toString();
        Toast.makeText(MainActivity.this,"Bạn chọn:"+s,Toast.LENGTH_LONG).show();
    }

    private void addControls() {
        radRatTuyetVoi= (RadioButton) findViewById(R.id.radRatTuyetVoi);
        radChapNhan= (RadioButton) findViewById(R.id.radTamChapNhan);
        radKhongTot= (RadioButton) findViewById(R.id.radKhongTot);
        radTuyeVoi= (RadioButton) findViewById(R.id.radTuyetVoi);
        btnBinhChon= (Button) findViewById(R.id.btnBinhChon);
    }
}

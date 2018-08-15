package com.example.tuans.hocmo1manhinh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void xuLyMoMotManHinh(View view) {
        Intent i=new Intent(MainActivity.this,ManHinh2Activity.class);
        //gửi lệnh tới android system;
        startActivity(i);
    }
}

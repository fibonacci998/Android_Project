package com.example.tuans.hocmomanhinhtruyendulieu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.tuans.model.SinhVien;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void xuLyMoGuiDuLieu(View view) {
        Intent intent=new Intent(MainActivity.this,ManHinh2Activity.class);
        intent.putExtra("KIEU_BOOLEAN",true);
        intent.putExtra("KIEU_CHAR",'x');
        intent.putExtra("KIEU_INT",100);
        intent.putExtra("KIEU_DOUBLE",4.3);
        intent.putExtra("KIEU_CHUOI","PAT");
        SinhVien temp=new SinhVien(1,"tuan");
        intent.putExtra("SINH_VIEN",temp);
        startActivity(intent);

    }
}

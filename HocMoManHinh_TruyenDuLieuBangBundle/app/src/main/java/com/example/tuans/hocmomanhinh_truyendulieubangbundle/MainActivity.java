package com.example.tuans.hocmomanhinh_truyendulieubangbundle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.tuans.model.SanPham;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void xuLyBundle(View view) {
        Intent intent=new Intent(MainActivity.this,ManHinh2Activity.class);
        Bundle bundle=new Bundle();
        bundle.putInt("X",113);
        bundle.putDouble("D",113.3);
        SanPham coco=new SanPham(1,"coco",100);
        bundle.putSerializable("SanPham",coco);
        intent.putExtra("MyBundle",bundle);
        startActivity(intent);
    }
}

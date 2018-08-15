package com.example.tuans.hoclistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import com.example.tuans.adapter.hinhAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    GridView gvHinh;
    ArrayList<Integer> dsHinh;
    hinhAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControl();
        addEvents();
    }

    private void addControl() {
        gvHinh= (GridView) findViewById(R.id.gvHinh);
        dsHinh= new ArrayList<>();
        dsHinh.add(R.drawable.h1);dsHinh.add(R.drawable.h2);
        dsHinh.add(R.drawable.h3);dsHinh.add(R.drawable.h4);
        dsHinh.add(R.drawable.h5);dsHinh.add(R.drawable.h6);
        dsHinh.add(R.drawable.h7);dsHinh.add(R.drawable.h8);
        dsHinh.add(R.drawable.h9);dsHinh.add(R.drawable.h10);
        dsHinh.add(R.drawable.h11);dsHinh.add(R.drawable.h12);
        dsHinh.add(R.drawable.h13);dsHinh.add(R.drawable.h14);
        dsHinh.add(R.drawable.h15);dsHinh.add(R.drawable.h16);
        dsHinh.add(R.drawable.h17);dsHinh.add(R.drawable.h18);
        adapter=new hinhAdapter(MainActivity.this,R.layout.item,dsHinh);
        gvHinh.setAdapter(adapter);
    }

    private void addEvents() {

    }
}

package com.example.tuans.listviewadvance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import adapter.DanhBaAdapter;
import model.DanhBa;

public class MainActivity extends AppCompatActivity {
    ListView lvDanhBa;
    ArrayList<DanhBa> dsDanhBa;
    DanhBaAdapter danhBaAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addControls() {
        lvDanhBa=findViewById(R.id.lvDanhBa);
        dsDanhBa=new ArrayList<>();
        dsDanhBa.add(new DanhBa(1,"Nguyen Van Teo","09429434"));
        dsDanhBa.add(new DanhBa(2,"Nguyen Van To","0934524"));
        dsDanhBa.add(new DanhBa(3,"Nguyen Van Te","0963462324"));
        danhBaAdapter=new DanhBaAdapter(MainActivity.this,R.layout.item1,dsDanhBa);
        lvDanhBa.setAdapter(danhBaAdapter);
    }

    private void addEvents() {

    }
}

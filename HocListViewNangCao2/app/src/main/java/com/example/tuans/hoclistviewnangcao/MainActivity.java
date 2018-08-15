package com.example.tuans.hoclistviewnangcao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.tuans.adapter.DanhBaAdapter;
import com.example.tuans.model.DanhBa;

import java.util.ArrayList;

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

    private void addEvents() {
    }

    private void addControls() {
        lvDanhBa=findViewById(R.id.lvDanhBa);
        dsDanhBa=new ArrayList<>();
        dsDanhBa.add(new DanhBa(1,"Tuan","092783324"));
        dsDanhBa.add(new DanhBa(2,"Teo","092732383"));
        //dsDanhBa.add(new DanhBa(3,"Doa","092782323"));
        danhBaAdapter =new DanhBaAdapter(MainActivity.this,R.layout.item,dsDanhBa);
        lvDanhBa.setAdapter(danhBaAdapter);
    }
}

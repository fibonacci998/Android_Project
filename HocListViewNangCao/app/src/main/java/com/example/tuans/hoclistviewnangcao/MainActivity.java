package com.example.tuans.hoclistviewnangcao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.tuans.adapter.DanhBaAdapter;
import com.example.tuans.model.DanhBa;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvDanhBa;
    ArrayList<DanhBa>dsDanhBa;
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
        lvDanhBa= (ListView) findViewById(R.id.lvDanhBa);
        dsDanhBa=new ArrayList<>();
        dsDanhBa.add(new DanhBa(1,"Tuasvij","094746282"));
        dsDanhBa.add(new DanhBa(2,"sivubsivb","0947247724"));
        dsDanhBa.add(new DanhBa(3,"isubvl","03824933"));
        danhBaAdapter=new DanhBaAdapter(MainActivity.this,R.layout.item,dsDanhBa);
        lvDanhBa.setAdapter(danhBaAdapter);
    }
}

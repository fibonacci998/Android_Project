package com.example.tuans.hoclistviewcoban;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView lvThu;
    String arrThu[];
    ArrayAdapter<String>adapterThu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        lvThu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,"Bạn chọn "+arrThu[position],Toast.LENGTH_LONG).show();
            }
        });
    }

    private void addControls() {
        arrThu=getResources().getStringArray(R.array.arrThu);
        adapterThu=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,arrThu);
        lvThu= (ListView) findViewById(R.id.lvThu);
        lvThu.setAdapter(adapterThu);
    }
}

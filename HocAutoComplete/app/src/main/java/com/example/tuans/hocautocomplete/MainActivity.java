package com.example.tuans.hocautocomplete;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText txtTen;
    AutoCompleteTextView autoTinh;
    Button btnXacNhan;
    TextView txtThongTin;
    String arrTinh[];
    ArrayAdapter<String>adapterTinh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XuLy();
            }
        });
    }

    private void XuLy() {
        String s=txtTen.getText().toString()+"\n"+autoTinh.getText().toString();
        txtThongTin.setText(s);
    }

    private void addControls() {
        txtTen= (EditText) findViewById(R.id.txtTen);
        txtThongTin= (TextView) findViewById(R.id.txtThongTin);
        btnXacNhan= (Button) findViewById(R.id.btnXacNhan);
        autoTinh= (AutoCompleteTextView) findViewById(R.id.autotxtTinh);
        arrTinh=getResources().getStringArray(R.array.arrTinhThanh);
        adapterTinh=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,arrTinh);
        autoTinh.setAdapter(adapterTinh);
    }
}

package com.example.tuans.hocmomanhinh_truyendulieubangbundle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.tuans.model.SanPham;

public class ManHinh2Activity extends AppCompatActivity {
    TextView txtKetqua;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh2);
        addControls();
    }

    private void addControls() {
        txtKetqua=findViewById(R.id.txtKetQua);
        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("MyBundle");
        SanPham sp= (SanPham) bundle.getSerializable("SanPham");
        txtKetqua.setText(
                "X="+bundle.getInt("X")+'\n'+
                        "D"+bundle.getDouble("D")+'\n'+
                        "SP="+sp
        );
    }
}

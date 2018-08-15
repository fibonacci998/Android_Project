package com.example.tuans.hocmomanhinhtruyendulieu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.tuans.model.SinhVien;

public class ManHinh2Activity extends AppCompatActivity {

    TextView txtKetQua;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh2);
        addControls();
    }

    private void addControls() {
        txtKetQua=findViewById(R.id.txtKetQua);
        Intent intent=getIntent();
        boolean kieuBoolean=intent.getBooleanExtra("KIEU_BOOLEAN",false);
        char kieuChar=intent.getCharExtra("KIEU_CHAR",'w');
        int kieuInt=intent.getIntExtra("KIEU_INT",0);
        double kieuDouble=intent.getDoubleExtra("KIEU_DOUBLE",0.0);
        String kieuChuoi=intent.getStringExtra("KIEU_CHUOI");
        SinhVien temp= (SinhVien) intent.getSerializableExtra("SINH_VIEN");
        txtKetQua.setText("Kiểu bool = "+kieuBoolean+'\n'+
        "Kiểu char="+kieuChar+'\n'+"Kiểu int="+kieuInt+'\n'+"Kiểu double="+kieuDouble+'\n'+
        "Kiểu chuỗi="+kieuChuoi+"Sinh viên: "+temp
        );
    }
}

package com.example.tuans.hocquanlyketquatrave;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ManHinhXuLiActivityActivity extends AppCompatActivity {
    TextView txtNhan,txt;
    Button btnTinh;
    Intent intent;
    int a,b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_xu_li_activity);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnTinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLiTinhUCLN();
            }
        });
    }

    private void xuLiTinhUCLN() {
        int min=Math.min(a,b);
        int UCLN=1;
        for (int i=min;i>=1;i--){
            if (a%i==0 && b%i==0){
                UCLN=i;
                break;
            }
        }
        //thay đổi thông tin, gán mới vào intent
        intent.putExtra("UCLN",UCLN);
        //đánh dấu kết quả trả về
        setResult(33,intent);
        //đóng màn hình này để màn hình MainActivity thành foreground lifetime
        //vì nó chỉ tự động nhận kq trả về trong foreground lifetime
        finish();
    }


    private void addControls() {
        txtNhan=findViewById(R.id.txtNhan);
        btnTinh=findViewById(R.id.btnTinhUCLN);
        intent=getIntent();
        a=intent.getIntExtra("a",-1);
        b=intent.getIntExtra("b",-1);
        txtNhan.setText("a="+a+" b="+b);

    }
}

package com.example.tuans.hocquanlyketquatrave;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText txtA,txtB;
    Button btnXuLi;
    TextView txtKetQua;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnXuLi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLiLayUCLN();
            }
        });
    }

    private void xuLiLayUCLN() {
        Intent intent=new Intent(MainActivity.this,ManHinhXuLiActivityActivity.class);
        intent.putExtra("a",Integer.parseInt(txtA.getText().toString()));
        intent.putExtra("b",Integer.parseInt(txtB.getText().toString()));
        startActivityForResult(intent,99);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==99 && resultCode==33){
            int temp=data.getIntExtra("UCLN",-1);
            txtKetQua.setText("UCLN ="+temp);
            int a=temp;
        }
    }

    private void addControls() {
        txtA=findViewById(R.id.txtA);
        txtB=findViewById(R.id.txtB);
        btnXuLi=findViewById(R.id.btnXuLi);
        txtKetQua=findViewById(R.id.txtKetQua);

    }
}

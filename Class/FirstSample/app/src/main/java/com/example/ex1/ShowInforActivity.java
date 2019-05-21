package com.example.ex1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import Model.Human;

public class ShowInforActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_infor);
        final Intent intent = getIntent();


        Button btShow = findViewById(R.id.btShow);
        btShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView edInfor = findViewById(R.id.tvInfor);

                Human human = (Human) intent.getSerializableExtra("human");
                edInfor.setText("Name: "+human.getName()+",\n Phone:"+human.getPhone()+",\n gender:"+ (human.isGender()?"Female":"Male"));
            }
        });
    }
}

package com.example.ex1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import Model.Human;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btSubmit = findViewById(R.id.btSubmit);
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etName = findViewById(R.id.etName);
                EditText etPhone = findViewById(R.id.etPhone);
                RadioGroup rgGender = findViewById(R.id.rgGender);

                String name = etName.getText().toString();
                String phone = etPhone.getText().toString();
                Boolean gender = true;//female
                RadioButton rgChoose = findViewById(rgGender.getCheckedRadioButtonId());
                if (rgChoose == findViewById(R.id.rdMale)){
                    gender = false; //male
                }

                Human human = new Human(name, phone, gender);

                Intent intent = new Intent(MainActivity.this, ShowInforActivity.class);
                intent.putExtra("human", human);
                startActivity(intent);
            }
        });
    }
}

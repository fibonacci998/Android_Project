package com.example.startup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    private TextView txtName, txtSalary, txtPhone, txtAddress, txtEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initComponent();
        initValue();
    }
    void initComponent(){
        txtName = findViewById(R.id.txtName);
        txtSalary = findViewById(R.id.txtSalary);
        txtAddress = findViewById(R.id.txtAddress);
        txtEmail = findViewById(R.id.txtEmail);
        txtPhone = findViewById(R.id.txtPhone);
    }
    void initValue(){
        Intent intent = getIntent();
        Employee person = (Employee) intent.getSerializableExtra("person");
        txtName.setText(person.getName());
        txtAddress.setText(person.getAddress());
        txtEmail.setText(person.getEmail());
        txtSalary.setText(String.valueOf(person.getSalary()));
        txtPhone.setText(person.getPhone());
    }
}

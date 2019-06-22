package com.example.startup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AddActivity extends AppCompatActivity {

    private TextView txtName, txtSalary, txtPhone, txtAddress, txtEmail;
    private Button btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        initComponent();
        initEvent();
    }
    void initComponent(){
        txtName = findViewById(R.id.txtName);
        txtSalary = findViewById(R.id.txtSalary);
        txtAddress = findViewById(R.id.txtAddress);
        txtEmail = findViewById(R.id.txtEmail);
        txtPhone = findViewById(R.id.txtPhone);
        btnAdd = findViewById(R.id.btnAdd);
    }
    void initEvent(){
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Employee person = new Employee();
                person.setName(txtName.getText().toString());
                person.setEmail(txtEmail.getText().toString());
                person.setPhone(txtPhone.getText().toString());
                person.setSalary(Float.valueOf(txtSalary.getText().toString()));
                person.setAddress(txtAddress.getText().toString());

                Intent intent = new Intent();
                intent.putExtra("person",person);
                setResult(200,intent);
                finish();
            }
        });
    }
}

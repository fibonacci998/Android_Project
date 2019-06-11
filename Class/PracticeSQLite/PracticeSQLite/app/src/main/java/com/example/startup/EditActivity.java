package com.example.startup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EditActivity extends AppCompatActivity {

    TextView txtName, txtAge, txtAddress, txtPhone, txtSalary;
    Button btnEdit;
    Person person;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        initComponent();
        initEvent();
    }
    void initEvent(){
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                person.setName(txtName.getText().toString());
                person.setAddress(txtAddress.getText().toString());
                person.setPhone(txtPhone.getText().toString());
                person.setAge(Integer.parseInt(txtAge.getText().toString()));
                person.setSalary(Float.parseFloat(txtSalary.getText().toString()));

                Intent intent = new Intent();
                intent.putExtra("person",person);
                setResult(300,intent);
                finish();

            }
        });
    }
    void initComponent(){
        Intent intent = getIntent();
        person = (Person) intent.getSerializableExtra("person");
        txtName = findViewById(R.id.txtName);
        txtAddress = findViewById(R.id.txtAddress);
        txtPhone = findViewById(R.id.txtPhone);
        txtAge = findViewById(R.id.txtAge);
        txtSalary = findViewById(R.id.txtSalary);
        btnEdit = findViewById(R.id.btnEdit);

        txtName.setText(person.getName());
        txtSalary.setText(String.valueOf(person.getSalary()));
        txtAge.setText(String.valueOf(person.getAge()));
        txtPhone.setText(person.getPhone());
        txtAddress.setText(person.getAddress());


    }
}
